package com.newland.home.springanno.pojo;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author ${linfengpeng}
 * @Title: Cat
 * @ProjectName Springboot
 * @Description: 实现InitializingBean,DisposableBean自定义初始化和销毁逻辑
 * @date 2020/9/27 9:48
 */
@Component
public class Cat implements InitializingBean,DisposableBean {
    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
