package com.newland.configure.center.springboottestapp.test;

import org.quartz.Job;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author ${林锋鹏}
 * @Title: TRepeatTask
 * @ProjectName bbf
 * @Description: 定时拉取任务
 * @date 2021/3/3 14:31
 */
public class TRepeatTask implements Job {
    private final static Logger LOG = LoggerFactory.getLogger(TRepeatTask.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        String description1 = jobExecutionContext.getTrigger().getDescription();
        System.out.println(description1);

    }



}
