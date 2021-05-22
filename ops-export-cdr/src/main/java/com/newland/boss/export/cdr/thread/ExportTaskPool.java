package com.newland.boss.export.cdr.thread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ${linfengpeng}
 * @Title: ExportTaskPool
 * @ProjectName ops-extract-cdr
 * @Description: 线程池
 * @date 2020/8/24 9:14
 */
public class ExportTaskPool {
    private static ThreadPoolExecutor threadPool = null;
    private static Log logger = LogFactory.getLog(ExportTaskPool.class);

    static {
        threadPool = new ThreadPoolExecutor(
                50,200,300, TimeUnit.SECONDS,new ArrayBlockingQueue<>(200),new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public static  void addExportTask(Runnable exportTask){
        while (getWaitQueueSize(threadPool.getQueue()) >= 200){
            try {
                logger.info("待处理队列满");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                logger.error("线程池异常",e);
            }
        }
        threadPool.execute(exportTask);
    }

    public static int getWaitQueueSize(Queue<?> queue){
        return queue.size();
    }
}
