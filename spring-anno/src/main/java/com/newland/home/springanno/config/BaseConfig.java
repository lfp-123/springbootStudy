package com.newland.home.springanno.config;

import com.newland.home.springanno.pojo.Person;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * @author ${linfengpeng}
 * @Title: BaseConfig
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2020/9/24 9:48
 */
@Configuration //告诉spring 容器这是一个配置类 代替xml
//@ComponentScan(
//        value = "com.newland.home",excludeFilters = {
//        @ComponentScan.Filter(
//                type =
//                        FilterType.ANNOTATION,classes = {Controller.class,Service.class}
//                FilterType.CUSTOM,classes = MyTypeFilter.class
//        )
//})
//相当于包扫描
//values 指定要扫描的包
//FilterType.ANNOTATION  按照注解的方式
//FilterType.CUSTON 自定义规则

@ComponentScan (value = "com.newland.home",includeFilters = {
        @ComponentScan.Filter(type = FilterType.CUSTOM,classes = MyTypeFilter.class)
},useDefaultFilters = false)
public class BaseConfig {

    //给容器中注册一个Bean ;类型返回类型
    @Bean(value = "person01")  //value指定注解名称
    public Person person01(){
        return new Person("lisi","14");
    }
}
