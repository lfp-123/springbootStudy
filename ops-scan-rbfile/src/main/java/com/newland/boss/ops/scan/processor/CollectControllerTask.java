package com.newland.boss.ops.scan.processor;

import com.newland.boss.bbf.middleware.mq.activemq.ActiveMqTemplate;
import com.newland.boss.bbf.third.sftp.SftpTemplate;
import com.newland.boss.bbf.third.sftp.jsch.JschSftpTemplate;
import com.newland.boss.ops.scan.bean.ResourceConfig;
import com.newland.boss.ops.scan.service.serviceImpl.DataResourceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lfp
 * @Title: CollectTaskThread
 * @ProjectName ops
 * @Description: TODO
 * @date 2021/1/22 10:12
 */
@Component
public class CollectControllerTask {

    @Autowired
    private DataResourceImpl dataResource;
    private ActiveMqTemplate activeMqTemplate;
    private List<ResourceConfig> resourceList = null;
    private CountDownLatch countDown = new CountDownLatch(1);

    public  void initialize() {
        activeMqTemplate = new ActiveMqTemplate();
        try {
            activeMqTemplate.connect("","","");
        } catch (Exception e) {
            e.printStackTrace();
        }
        resourceList = dataResource.getResourceList();
    }
    public void doCollect() {
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(resourceList.size());
            for (int task = 0, taskNum = resourceList.size(); task < taskNum; task++) {
                ResourceConfig resourceConfig = resourceList.get(task);
                try {
                    SftpTemplate sftpTemplate = new JschSftpTemplate();
                    sftpTemplate.connect(resourceConfig.getRemoteAddr(), Integer.parseInt(resourceConfig.getRemotePort()), resourceConfig.getRemoteUser(), resourceConfig.getRemotePwd());
                    executorService.submit(new CollectFileTaskThread(resourceConfig,sftpTemplate,activeMqTemplate));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            countDown.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
