package com.newland.home.springanno.config;

import com.newland.home.springanno.conditional.*;
import com.newland.home.springanno.pojo.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author ${linfengpeng}
 * @Title: MyConfig3
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2020/9/24 14:07
 */
@Configuration
@Import({Red.class, RedSelector.class,MyImportRegisterar.class})
public class BaseConfig3 {

    @Conditional(LinuxContional.class)
    @Bean(value = "linux")
    public Person person(){
        return new Person("bill","50");
    }

    @Conditional(WindowsCondional.class)
    @Bean(value = "Windows",initMethod ="",destroyMethod = "")
    public Person person1(){
        return new Person("bill1","51");
    }

    @Bean(value = "bill")
    public Person person2(){
        return new Person("bill","52");
    }
}
