package com.newland.boss.export.cdr.service;


import com.newland.boss.export.cdr.bean.QueryCdrOperation;
import org.apache.hadoop.hbase.client.Result;

import java.util.List;
import java.util.Map;

/**
 * @author ${linfengpeng}
 * @Title: QueryCdrOperation
 * @ProjectName ops-export-cdr
 * @Description: 接口
 * @date 2020/8/10 14:16
 */
public interface QueryCdrOperationService {
    /**
     * 查询待处理的工单
     *
     * @return
     */
    List<QueryCdrOperation> queryCdrOperationList();

    /**
     * 更新时间
     */
    void insertCdrOperation(QueryCdrOperation cdr);



}
