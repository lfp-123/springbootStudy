package com.newland.boss.ops.scan.bean;


import com.newland.boss.bbf.third.sftp.SftpTemplate;
import com.newland.boss.bbf.third.sftp.jsch.JschSftpTemplate;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * @author lfp
 * @Title: ConfigBean
 * @ProjectName ops-scan-rbfile
 * @Description: 配置类
 * @date 2020/12/9 10:48
 */
@Component
@EnableJms
public class ConfigBean {

    @Value("${queueName}")
    private String queueName;

    @Value("${MqfilePath}")
    private String path;

    @Bean
    public Queue getQueue(){
        return new ActiveMQQueue(queueName);
    }
    @Bean
    public String getPath(){
        return path;
    }

    @Bean
    public SftpTemplate getSftpTemplate(){
        return  new JschSftpTemplate();
    }


    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
