package com.newland.boss.ops.scan.mapper;

import com.newland.boss.ops.scan.bean.ResourceConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lfp
 * @Title: ResourcesDao
 * @ProjectName ops
 * @Description:
 * @date 2021/1/21 18:30
 */
@Repository
@Mapper
public interface ResourcesDao {

    public List<ResourceConfig> queryCFGAll();
    public int queryCFGcount();
 }
