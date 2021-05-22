package com.newland.netty.study.springbootnettynio.test;

import io.netty.util.NettyRuntime;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author lfp
 * @Title: Test1
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2020/12/25 14:00
 */
public class Test1 {

    public static String readValue(String filePath,String key) {
        Properties props = new Properties();
        try {
            InputStream in = new BufferedInputStream (new FileInputStream(filePath));
            props.load(in);
            String value = props.getProperty (key);
            System.out.println(key+" = "+value);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //读取properties的全部信息
    public static void readProperties(String filePath) {
        Properties props = new Properties();
        try {
            InputStream in = new BufferedInputStream (new FileInputStream(filePath));
            props.load(in);
            Enumeration en = props.propertyNames();
            while (en.hasMoreElements()) {
                String key = (String) en.nextElement();
                String Property = props.getProperty (key);
                System.out.println(key+Property);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //写入properties信息
    public static void writeProperties(String filePath,String parameterName,String parameterValue) {
        Properties prop = new Properties();
        try {
            InputStream fis = new FileInputStream(filePath);
            //从输入流中读取属性列表（键和元素对）
            prop.load(fis);
            OutputStream fos = new FileOutputStream(filePath);
            prop.setProperty(parameterName, parameterValue);
            //以适合使用 load 方法加载到 Properties 表中的格式，
            //将此 Properties 表中的属性列表（键和元素对）写入输出流
            prop.store(fos,"finished");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Visit "+filePath+" for updating "+parameterName+" value error");
        }
    }

    public static void main(String[] args) {
        String filePath = System.getProperty("user.dir")+"\\bbf.cfg";
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("创建文件");
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeProperties(filePath,"age","21");
        readProperties(filePath);
        System.out.println("OK");
    }
}
