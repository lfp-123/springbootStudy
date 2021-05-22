package com.newland.ops.del.cdr.opsdelcdr.service.serviceImpl;

import com.newland.ops.del.cdr.opsdelcdr.bean.Constant;
import com.newland.ops.del.cdr.opsdelcdr.bean.Rowkey;
import com.newland.ops.del.cdr.opsdelcdr.service.cdrService;
import oracle.jdbc.driver.Const;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.omg.CORBA.OBJ_ADAPTER;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author ${linfengpeng}
 * @Title: cdrServiceImpl
 * @ProjectName TempProject
 * @Description: TODO
 * @date 2020/11/2 16:29
 */
@Service
public class cdrServiceImpl implements cdrService {
    private static Log logger = LogFactory.getLog(cdrServiceImpl.class);
    public static AtomicInteger count = new AtomicInteger(0);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void delCdr(String operationId) throws SQLException {
        if("".equals(operationId)|| operationId == null){
            jdbcTemplate.update(Constant.sqlCA);
            jdbcTemplate.update(Constant.sqlOA);
            jdbcTemplate.update(Constant.sqlPA);
            jdbcTemplate.update(Constant.sqlRA);
        }else {
          jdbcTemplate.update(Constant.sqlC,operationId);
            logger.info("delete over c");
          jdbcTemplate.update(Constant.sqlO,operationId);
            logger.info("delete over o");
          jdbcTemplate.update(Constant.sqlP,operationId);
            logger.info("delete over p");
//            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//10.46.158.216/billing", "billlog", "billlog");
//            PreparedStatement ps = conn.prepareStatement(Constant.sqlQR);
//            PreparedStatement ds = conn.prepareStatement(Constant.sqlR);
//            ps.setObject(1,operationId);
//            int size = 0;
//            do {
//                ResultSet rs = ps.executeQuery();
//                while (rs.next()){
//                    ds.setObject(1,rs.getString("rowkey"));
//                    ds.addBatch();
//                    size++;
//                }
//                rs.close();
//                ds.executeBatch();
//                logger.info("delete" +size);
//            }while (getCount(conn)>0);
            for (int i = 0; i < 20; i++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true){
                            ArrayList<Object[]> rowkeysR =  new ArrayList<>();
                            jdbcTemplate.query(Constant.sqlQR, new Object[]{operationId}, resultSet -> {
                                String rowkey = resultSet.getString("ROWKEY");
                                rowkeysR.add(new Object[]{rowkey});
                            });
                            if(rowkeysR.size()==0){
                                logger.info("delete over r");
                                break;
                            }else {
                                int[] ints = jdbcTemplate.batchUpdate(Constant.sqlR, rowkeysR);
                                rowkeysR.clear();
                                count.addAndGet(ints.length);
                            }
                            logger.info("delete " +count.get());
                        }
                    }
                }).start();
            }

        }
    }

    public long getCount(Connection conn) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(Constant.sqlCount);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            long count = rs.getInt("count");
            return count;
        }
        return 0L;

    }
}
