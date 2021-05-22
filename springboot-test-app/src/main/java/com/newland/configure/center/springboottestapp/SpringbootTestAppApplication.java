package com.newland.configure.center.springboottestapp;

import com.newland.boss.bbf.config.ConfigCenter;
import com.newland.boss.bbf.config.impl.ConfigCenterBuilder;

import java.io.Serializable;
import java.util.Map;


public class SpringbootTestAppApplication {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("org.terracotta.quartz.skipUpdateCheck","true");
     //   while (true){
       //     Scanner scanner = new Scanner(System.in);
        //    System.out.println("请刷新配置： ");
     //       String String  = scanner.next();
            ConfigCenter configCenter = ConfigCenterBuilder.build();
            Map<String, Serializable> configAll = configCenter.getAll();
            System.out.println("config num size : "+configAll.size());
      //  }

    }
}
