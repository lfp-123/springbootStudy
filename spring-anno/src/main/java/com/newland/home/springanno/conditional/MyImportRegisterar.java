package com.newland.home.springanno.conditional;

import com.newland.home.springanno.pojo.Rainbow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author ${linfengpeng}
 * @Title: MyImportRegisterar
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2020/9/24 15:27
 */
public class MyImportRegisterar implements ImportBeanDefinitionRegistrar {
    /**
     *
     * @param importingClassMetadata : 当前类的注册信息
     * @param registry : 注册类
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean red = registry.containsBeanDefinition("com.newland.home.springanno.pojo.Blue");
        boolean blue = registry.containsBeanDefinition("com.newland.home.springanno.pojo.Write");
        if(red && blue){
            RootBeanDefinition rootbean = new RootBeanDefinition(Rainbow.class);
            //指定bean 名
            registry.registerBeanDefinition("rainbow",rootbean);
        }

    }
}
