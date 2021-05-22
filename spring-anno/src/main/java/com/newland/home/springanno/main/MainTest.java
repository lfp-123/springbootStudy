package com.newland.home.springanno.main;

import com.newland.home.springanno.config.BaseConfig;
import com.newland.home.springanno.pojo.Person;
import org.springframework.boot.autoconfigure.web.reactive.HttpHandlerAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ${linfengpeng}
 * @Title: MainTest
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2020/9/24 9:56
 */
public class MainTest {
    public static void main(String[] args) {
//        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("bean.xml");
//        Object person = classPathXmlApplicationContext.getBean("person");
//        System.out.println(person.toString());

        AnnotationConfigApplicationContext annoConfig =
                new AnnotationConfigApplicationContext(BaseConfig.class);
        Person bean = annoConfig.getBean(Person.class);
        System.out.println(bean);
        String[] beanNamesForType = annoConfig.getBeanNamesForType(Person.class);
        for (String s : beanNamesForType) {
            System.out.println(s);
        }
        System.out.println("---------------------");

    }
}
