package com.newland.home.springanno.config;

import com.newland.home.springanno.pojo.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author ${linfengpeng}
 * @Title: BaseConfig2
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2020/9/24 11:08
 */
@Configuration
public class BaseConfig2 {

    /**
     * prototype:多实例
     * singleton:单例
     * request：同一个请求创建一个实例
     * session ：同一个session创建一个实例
     * @return
     */
//    @Scope("prototype")
    @Bean(value = "person")
    public Person person(){
        return new Person("zhangsan","13");
    }
}
