package com.newland.home.springanno.conditional;


import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.io.InputStream;

/**
 * @author ${linfengpeng}
 * @Title: BaseCondinal
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2020/9/24 14:35
 */
public class BaseCondinal implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //1 获取环境变量
        Environment environment = context.getEnvironment();
        String property = environment.getProperty("os.name");
        //2 获取bean工厂
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        //3 类加载器
        ClassLoader classLoader = context.getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream("");
        //4 已经注册的bean
        BeanDefinitionRegistry registry = context.getRegistry();
        String[] beanDefinitionNames = registry.getBeanDefinitionNames();

        return false;
    }
}
