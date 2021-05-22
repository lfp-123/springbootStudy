package com.newland.boss.ops.scan.service.serviceImpl;

import com.newland.boss.ops.scan.bean.ResourceConfig;
import com.newland.boss.ops.scan.mapper.ResourcesDao;
import com.newland.boss.ops.scan.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lfp
 * @Title: ResurcesServiceImpl
 * @ProjectName ops
 * @Description: TODO
 * @date 2021/1/21 18:55
 */
@Service
public class ResourcesServiceImpl implements ResourceService {
    @Autowired
    private ResourcesDao resourcesDao;

    @Override
    public List<ResourceConfig> queryCFGAll() {
        return resourcesDao.queryCFGAll();
    }

    @Override
    public int queryCFGcount() {
       return resourcesDao.queryCFGcount();
    }
}
