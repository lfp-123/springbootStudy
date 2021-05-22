package com.newland.boss.export.cdr.service.serviceImpl;


import com.newland.boss.export.cdr.bean.QueryCdrOperation;
import com.newland.boss.export.cdr.mapper.CdrOperationMapper;
import com.newland.boss.export.cdr.service.QueryCdrOperationService;
import com.newland.boss.export.cdr.util.DButil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

/**
 * @author ${linfengpeng}
 * @Title: CdrOperationImpl
 * @ProjectName ops-export-cdr
 * @Description: 接口实现
 * @date 2020/8/10 14:18
 */
public class CdrOperationImpl implements QueryCdrOperationService
{

    /**
     * 查找导出的工单
     * @return
     */
    @Override
    public List<QueryCdrOperation> queryCdrOperationList() {
        SqlSession session = new DButil().getSession();
        CdrOperationMapper mapper = session.getMapper(CdrOperationMapper.class);
        List<QueryCdrOperation> cdrList = mapper.queryCdrOperationList();
        return  cdrList;
    }

    /**
     * 更新状态
     * @param cdr
     */
    @Override
    public synchronized void  insertCdrOperation(QueryCdrOperation cdr) {
        SqlSession session = new DButil().getSession();
        CdrOperationMapper cdrMapper = session.getMapper(CdrOperationMapper.class);
        cdrMapper.updateCdrOperation(cdr);
        session.commit();
        session.close();
    }





}
