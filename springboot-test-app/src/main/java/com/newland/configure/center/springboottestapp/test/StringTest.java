package com.newland.configure.center.springboottestapp.test;



/**
 * @author ${林锋鹏}
 * @Title: StringTest
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/4/6 14:13
 */
public class StringTest {

    public static void main(String[] args) {

        String str = "K=V";
        String[] split = str.split("=");
        System.out.println(split[0]);
        System.out.println(split[1]);
    }
}
