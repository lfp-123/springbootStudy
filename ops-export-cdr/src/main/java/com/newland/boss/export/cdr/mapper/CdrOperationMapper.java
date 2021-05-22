package com.newland.boss.export.cdr.mapper;


import com.newland.boss.export.cdr.bean.QueryCdrOperation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ${linfengpeng}
 * @Title: CdrOperationMapper
 * @ProjectName ops-extract-cdr
 * @Description: 工单处理接口
 * @date 2020/8/10 9:38
 */
public interface CdrOperationMapper {
    /**
     * 查询待处理的工单
     * @return
     */
     List<QueryCdrOperation> queryCdrOperationList();
    /**
     * 更新时间
     */
    void updateCdrOperation(@Param("cdr") QueryCdrOperation cdr);

}
