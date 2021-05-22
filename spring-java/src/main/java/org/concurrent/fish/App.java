package org.concurrent.fish;

import java.lang.reflect.Executable;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App 
{
    private static volatile boolean run =true;
    public static void main( String[] args ) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        //可以获取每天晚上的12点
        LocalDateTime time = now.withHour(0)
                .withSecond(0)
                .withNano(0).withMinute(0);
        System.out.println(time);
        //延时时间
        long millis = Duration.between(now, time).toMillis();
        //间隔时间
        long period = 1000 * 60 * 60 * 24;
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(1);
        //当前时间延时millis后执行，间隔period执行
        threadPool.scheduleAtFixedRate(() -> System.out.println("running"),millis,period, TimeUnit.DAYS);
        //无法满足复杂的时间调度任务，需要手动解析crontab表达式
    }
}
