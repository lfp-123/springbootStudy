package com.newland.boss.springbootactivemq.bean;

        import org.apache.activemq.command.ActiveMQQueue;
        import org.springframework.beans.factory.annotation.Value;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.PropertySource;
        import org.springframework.jms.annotation.EnableJms;
        import org.springframework.stereotype.Component;

        import javax.jms.Queue;

/**
 * @author ${linfengpeng}
 * @Title: ConfigBean
 * @ProjectName hdfs0609
 * @Description: TODO
 * @date 2020/9/14 16:34
 */
@Component
@EnableJms
public class ConfigBean {

    @Value("${myqueue}")
    private String myqueue;

    @Bean
    public Queue queue(){
        return new ActiveMQQueue(myqueue);
    }
}
