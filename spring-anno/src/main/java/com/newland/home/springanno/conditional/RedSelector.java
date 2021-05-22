package com.newland.home.springanno.conditional;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author ${linfengpeng}
 * @Title: RedSelector
 * @ProjectName Springboot
 * @Description: 自定义逻辑，返回所需的组件
 * @date 2020/9/24 15:04
 */
public class RedSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //AnnotationMetadata 当前标注@Import 注解类的所有信息

        //返回一个空数组
        return new String[]{"com.newland.home.springanno.pojo.Blue","com.newland.home.springanno.pojo.Write"};
      //  return new String[0];
    }
}
