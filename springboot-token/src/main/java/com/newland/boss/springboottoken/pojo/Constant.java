package com.newland.boss.springboottoken.pojo;

import java.util.HashMap;

/**
 * @author lfp
 * @Title: Constant
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2020/12/15 18:27
 */
public class Constant {

    public final  static String req = "/login";
    public final  static  int  timeOut = 5*60*1000;

    private final static HashMap<String,User> maps = new HashMap<>();

    public static HashMap<String, User> getMaps() {
        return maps;
    }



}
