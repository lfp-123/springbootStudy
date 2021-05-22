package com.newland.boss.export.cdr.thread;


import com.newland.boss.export.cdr.bean.Constants;
import com.newland.boss.export.cdr.bean.QueryCdrOperation;
import com.newland.boss.export.cdr.bean.StatusConstant;
import com.newland.boss.export.cdr.service.serviceImpl.CdrOperationImpl;
import com.newland.boss.export.cdr.util.DButil;
import com.newland.boss.export.cdr.util.StringUtils;
import com.newland.boss.export.cdr.util.Tool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ExportTask implements Runnable {

	private static Log logger = LogFactory.getLog(ExportTask.class);
	private Long userId;
	private String batch_id;
	private CdrOperationImpl impl;
	private Map<String, String> userSqlMap;
	private Connection connection;
	private DButil dbUtil;
	private PreparedStatement ps;
	public List<String> fieldList = null;
	public List<String> homeList = null;

	private org.apache.hadoop.hbase.client.Connection hConnection;
	public static Configuration conf;

	public static int batch_commit_count = 500;
	public static int count = 0;

	public QueryCdrOperation cdr;

	static {
		try {
			conf = HBaseConfiguration.create();
			conf.addResource("hbase-site.xml");
			logger.info("read hbase.xml");
		} catch (Exception ex) {
			logger.error("读取hbsae配置文件异常", ex);
		}
	}

	public ExportTask(Map<String, String> userSqlMap, Long user_id, String batch_id, QueryCdrOperation cdr, CdrOperationImpl impl) {
		this.userId = user_id;
		this.batch_id = batch_id;
		this.userSqlMap = userSqlMap;
		this.cdr = cdr;
		this.impl = impl;
		this.dbUtil = new DButil();
	}

	@Override
	public void run() {
		String tableName = null;
		if(cdr.getOperatorType() != StatusConstant.OPERATION_EXPORT_TYPE_FIX ){
			tableName  = StringUtils.getTableName(cdr.getStartTime());
		}
		String startRow;
		String endRow;
		if(userId == null){
			startRow = StringUtils.getRowKey(cdr.getUserId(), cdr.getStartTime());
			endRow = StringUtils.getRowKey(cdr.getUserId(),cdr.getEndTime());
		}else {
			startRow = StringUtils.getRowKey(userId, cdr.getStartTime());
			endRow = StringUtils.getRowKey(userId,cdr.getEndTime());
		}
		logger.info("table:" + tableName + " start_rowkey:" + startRow + " stop_rowkey:"
				+ endRow);
		Table table = null;
		try {
			connection = dbUtil.getConnection();
			connection.setAutoCommit(false);
			if (logger.isDebugEnabled()) {
				logger.debug("Oracle connection" + connection);
			}
			cdr.setStatus(StatusConstant.QUERY_STATUS);
			impl.insertCdrOperation(cdr);
			hConnection = ConnectionFactory.createConnection(conf);
			table = hConnection.getTable(TableName.valueOf(tableName));
			Scan scan = new Scan();
			ResultScanner rs = null;
			scan.setScanMetricsEnabled(true);
			scan.setStartRow(startRow.getBytes());
			scan.setStopRow(endRow.getBytes());
			rs = table.getScanner(scan);
			String src_type = "";
			String last_src_type = "";
			cdr.setStatus(StatusConstant.PUT_ORACLE_STATUS);
			impl.insertCdrOperation(cdr);
			try {
				for (Result r : rs) {
					last_src_type = src_type;
					src_type = new String(r.getRow()).substring(35, 36);
					add2Oracle(r, src_type, last_src_type);
				}
			} catch (SQLException e) {
				logger.error("导入数据库add2Oracle失败", e);
				connection.rollback();
			}
			rs.close();
		} catch (Throwable e) {
			logger.error("导入数据库失败", e);
		} finally {
			try {
				if (null != table) {
					table.close();
				}
			} catch (IOException e1) {
				logger.error("table关闭失败", e1);
			}
			try {
				logger.info("------数据提交------" + count);
				commit();
				if (null != ps) {
					ps.close();
				}
				cdr.setStatus(StatusConstant.END_STATUS);
				impl.insertCdrOperation(cdr);
				connection.close();
				hConnection.close();
				logger.info("------数据提交成功------");
				logger.info("commit sql over");
			} catch (Exception e) {
				logger.error("数据提交失败", e);
			}
		}
	}

	public void add2Oracle(Result r, String src, String lastSrc) throws SQLException, UnsupportedEncodingException {
		count++;
		KeyValue[] aKeyValue = r.raw();
		if (!src.equalsIgnoreCase(lastSrc) && count != 1) {
			commit();
		}
		if (!src.equalsIgnoreCase(lastSrc) || count == 1) {
			ps = this.getPreparedStatement(src);
		}

		try {
			addBatch(r, aKeyValue, src);
			if (count % batch_commit_count == 0) {
				logger.info("commit count:" + count);
				commit();
				if (count % 10000 == 0) {
					ps.close();
					connection.close();
					connection = dbUtil.getConnection();
					connection.setAutoCommit(false);
					ps = this.getPreparedStatement(src);
				}
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			connection = dbUtil.getConnection();
			ps = this.getPreparedStatement(src);
			addBatch(r, aKeyValue, src);
			commit();
		} catch (Exception e) {
			logger.error("100条数据提交失败", e);
			logger.info(e.toString());
			logger.info(e.getMessage());
		}
	}

	public PreparedStatement getPreparedStatement(String src) throws SQLException {
		if (null != ps) {
			ps.close();
		}
		if (userSqlMap.isEmpty()) {
			logger.error("用户配置表无数据,无法导数据");
			throw new SQLException();
		}
		if ("r".equalsIgnoreCase(src)) {
			fieldList = Constants.data_format_r;
			homeList = Constants.data_format_other_r;
			ps = connection.prepareStatement(userSqlMap.get("r"));
		} else if ("c".equalsIgnoreCase(src)) {
			fieldList = Constants.data_format_c;
			ps = connection.prepareStatement(userSqlMap.get("c"));
		} else if ("p".equalsIgnoreCase(src)) {
			fieldList = Constants.data_format_p;
			ps = connection.prepareStatement(userSqlMap.get("p"));
		} else if ("o".equalsIgnoreCase(src)) {
			fieldList = Constants.data_format_o;
			ps = connection.prepareStatement(userSqlMap.get("o"));
		} else {
			ps = null;
		}
		if (homeList == null) {
			homeList = Arrays.asList("home_city");
		}
		return ps;
	}

	private void addBatch(Result result, KeyValue[] aKeyValue, String srctype)
			throws SQLException {
		ArrayList<String> dbList = new ArrayList<>();
		String detailCdr = Bytes.toString(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("detail")));
		List<String> detail = StringUtils.split(detailCdr, "|");
		String homeCityCdr = Bytes.toString(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("home_city")));
		List<String> homeCity = StringUtils.split(homeCityCdr, "|");
		dbList.add(new String(result.getRow()));
		String value ="";
		dbList.addAll(homeCity);
		dbList.addAll(detail);
		for (int i = 0; i < dbList.size(); i++) {
			try {
				value = dbList.get(i);
			}catch (Exception e){
				value = "";
			}
			ps.setObject(i+1,value);
		}
		ps.setObject(dbList.size()+1,cdr.getOperationId());
		ps.setObject(dbList.size()+2,cdr.getOperatorId());
		ps.addBatch();
	}

	public void commit() throws SQLException {
		if (null != ps) {
			ps.executeBatch();
		}
		connection.commit();
		if (null != ps) {
			ps.clearBatch();
		}
	}

}
