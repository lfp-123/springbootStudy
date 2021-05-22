package com.newland.configure.center.springboottestapp.test;

import com.sun.xml.internal.ws.addressing.WsaActionUtil;

import javax.activation.CommandObject;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author ${林锋鹏}
 * @Title: TaskTimeout
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/4/26 13:53
 */
public class TaskTimeout {
    public static void main(String[] args) throws Exception {
        TaskTimeout taskTimeout = new TaskTimeout();
        List<Object> objects = taskTimeout.executeDBQuery();
        for (Object object : objects) {
            System.out.println(object);
        }
    }
    public List<Object> executeDBQuery() throws Exception {
        // 该处需防止内存溢出，后期应优化。
        // ArrayListHandler:将查询的结果，每一行先封装到Object数组中，然后将数据存入List集合
        ExecutorService executor = Executors.newSingleThreadExecutor();
        FutureTask<List<Object>> future = new FutureTask<List<Object>>(new Callable<List<Object>>() {
            @Override
            public List<Object> call() throws Exception {// 执行sql

                ArrayList<Object> objects = new ArrayList<Object>();
                objects.add("1");
                objects.add("2");
                objects.add("3");
                Thread.sleep(1000);

                return objects;
            }
        });
        String exeSqlTimeout = "6000";
        try {
            executor.execute(future);
            List<Object> result = future.get(1000, TimeUnit.MILLISECONDS);
            return result;
        } catch (TimeoutException e) { // sql执行超时
            throw new TimeoutException(
                    "Sql执行超时！系统规定Sql执行时间不能超过" + (Long.parseLong(exeSqlTimeout) / 1000 ) + "秒...");
        } catch (Exception e) {
            throw new RuntimeException("Sql执行异常:" + e.getMessage());
        } finally {
            future.cancel(true);
            executor.shutdown();
        }
    }



}



