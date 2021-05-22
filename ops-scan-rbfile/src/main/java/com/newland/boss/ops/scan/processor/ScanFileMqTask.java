package com.newland.boss.ops.scan.processor;


import com.newland.boss.ops.scan.bean.ResourceConfig;
import com.newland.boss.ops.scan.service.serviceImpl.ResourcesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lfp
 * @Title: ScanFileMqTask
 * @ProjectName ops-scan-rbfile
 * @Description: 处理类
 * @date 2020/12/9 10:53
 */
@Component
public class ScanFileMqTask {



    @Autowired
    private ResourcesServiceImpl resourcesService;


    public void collectTask(){
        List<ResourceConfig> resourceConfigs = resourcesService.queryCFGAll();
        System.out.println(resourceConfigs.toString());
    }


}
