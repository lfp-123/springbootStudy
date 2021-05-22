package com.newland.boss.redis.springboofredis.controller;

import com.google.gson.Gson;
import com.newland.boss.redis.springboofredis.dao.RedisDataDao;
import com.newland.boss.redis.springboofredis.service.impl.IRedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ${linfengpeng}
 * @Title: IRedisController
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2020/11/4 16:03
 */
@Component
public class IRedisController {
    @Autowired
    private IRedisServiceImpl impl;
    @Autowired
    private RedisDataDao dataDao;


    @PostConstruct
    public void testRedis(){
        long startTime = System.currentTimeMillis();
        Gson gson = new Gson();
        List<ConcurrentHashMap<String, ArrayList<Object>>> maps = dataDao.queryAllData();
        for (ConcurrentHashMap<String, ArrayList<Object>> map : maps) {
            for (String key : map.keySet()) {
                String value = gson.toJson(map.get(key));
                System.out.println(value);
                impl.setValue(key,value);
            }
        }
        long endTime = System.currentTimeMillis();
        float seconds = (endTime - startTime) / 1000;
        System.out.println(Float.toString(seconds) + " seconds.");
    }

    public void testSpeed(){
        long startTime = System.currentTimeMillis();
//        for (int i = 10000562; i < 10100562; i++) {
          //  long startTimeone = System.currentTimeMillis();
            impl.getValue(String.valueOf(10000001));
        //    long endTime = System.currentTimeMillis();
//        }
        long endTime = System.currentTimeMillis();
        float seconds = (endTime - startTime) / 1000;
        System.out.println(Float.toString(seconds) + " seconds.");
    }
}
