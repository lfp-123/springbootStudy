package java;

import com.newland.boss.bbf.config.ConfigCenter;
import com.newland.boss.bbf.config.impl.ConfigCenterBuilder;

import java.io.Serializable;
import java.util.Map;


public class SpringbootTestAppApplication {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("org.terracotta.quartz.skipUpdateCheck","true");
        ConfigCenter configCenter = ConfigCenterBuilder.build();
        Map<String, Serializable> configAll = configCenter.getAll();
        System.out.println("config num size : "+configAll.size());
    }
}
