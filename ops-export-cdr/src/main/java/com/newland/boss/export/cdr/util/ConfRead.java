package com.newland.boss.export.cdr.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author ${林锋鹏}
 * @Title: ConfRead
 * @ProjectName ops-export-cdr
 * @Description: 配置读取工具类
 * @date 2020/7/17 10:07
 */
public class ConfRead {
    public static String getConf(String conf){
        Properties properties = new Properties();
        InputStream inputStream = Object.class.getResourceAsStream("/conf.properties");
        String  getConf=null;
        try {
            properties.load(inputStream);
            getConf  =(String) properties.get(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return getConf;
    }
}
