package com.newland.configure.center.springboottestapp.test;



import com.newland.boss.bbf.context.BbfContext;
import io.netty.channel.ChannelHandlerContext;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author ${林锋鹏}
 * @Title: SchedulerStart
 * @ProjectName bbf
 * @Description: 调度启动类
 * @date 2021/3/4 14:49
 */
public class SchedulerStart {
    private final static StdSchedulerFactory sf  =new StdSchedulerFactory();
    private final static Logger LOG = LoggerFactory.getLogger(SchedulerStart.class);
    private static String JOB_GROUP_NAME = "JOBGROUP_NAME";
    private static String TRIGGER_GROUP_NAME = "TRIGGERGROUP_NAME";


    public static void StartUp(int index) {

            Scheduler scheduler = null;
            try {
                scheduler = sf.getScheduler();
                if(scheduler.isStarted()){
                    System.out.println("已经开启的话");
                    removeJob();
                }
                JobDetail jb = JobBuilder.newJob(TRepeatTask.class)
                        .withIdentity("jpull",TRIGGER_GROUP_NAME)
                        .withDescription("Time's up. Start the timing pull ,"+index)
                        .build();
                //3s后执行
                long time = System.currentTimeMillis() + 3 * 1000L;
                Date date = new Date(time);
                Trigger t = TriggerBuilder.newTrigger()
                        .withDescription("This is a timed pull configuration task")
                        .startAt(date)
                        .withIdentity("tpull",JOB_GROUP_NAME)
                        .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                        .build();
                scheduler.scheduleJob(jb, t);
                scheduler.start();
            } catch (SchedulerException e) {
                LOG.error(e.getMessage());
            }



    }

    public static void removeJob() {
        try {
            Scheduler sched = sf.getScheduler();
            sched.pauseTrigger(TriggerKey.triggerKey("tpull",TRIGGER_GROUP_NAME));
            sched.unscheduleJob(TriggerKey.triggerKey("jpull",TRIGGER_GROUP_NAME));
            sched.deleteJob(JobKey.jobKey("pull",JOB_GROUP_NAME));
            LOG.info("close all timer task");
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}
