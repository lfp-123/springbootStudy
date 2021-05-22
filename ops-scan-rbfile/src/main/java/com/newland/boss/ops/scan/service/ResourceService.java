package com.newland.boss.ops.scan.service;

import com.newland.boss.ops.scan.bean.ResourceConfig;

import java.util.List;

/**
 * @author lfp
 * @Title: ResourceService
 * @ProjectName ops
 * @Description: TODO
 * @date 2021/1/21 18:54
 */
public interface ResourceService {
    public List<ResourceConfig> queryCFGAll();
    public int queryCFGcount();
}
