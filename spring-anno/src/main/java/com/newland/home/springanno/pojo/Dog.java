package com.newland.home.springanno.pojo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author ${linfengpeng}
 * @Title: Dog
 * @ProjectName Springboot
 * @Description: bean生命周期方法
 * @date 2020/9/27 10:00
 */
@Component
public class Dog implements BeanPostProcessor {
    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //可以直接返回创建的bean ,也可以进行包装返回
        return null;
    }

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }
}
