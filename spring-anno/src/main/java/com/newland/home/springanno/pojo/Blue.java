package com.newland.home.springanno.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author ${linfengpeng}
 * @Title: Blue
 * @ProjectName Springboot
 * @Description: values注解使用
 * @date 2020/9/24 15:09
 */
@Component
public class Blue {
    @Value("蓝色")   //1 直接赋值
    private String color;

    @Value("#{20-1}")  //2 使用
    private String age;

    @Value("${Test.Name}") //读取配置文件信息
    private String resources;
}
