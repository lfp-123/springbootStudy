package com.newland.boss.redis.springboofredis.dao;

import com.sun.rowset.internal.Row;
import io.netty.util.internal.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ${linfengpeng}
 * @Title: RedisDataDao
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2020/11/6 10:46
 */
@Repository
public class RedisDataDao {
    @Autowired
    private JdbcTemplate jdbc;

    public List<ConcurrentHashMap<String,ArrayList<Object>>> queryAllData(){
        ArrayList<ConcurrentHashMap<String, ArrayList<Object>>> listMaps = new ArrayList<>();
        String sql  = "select * from redis_test_data";
        List rows = jdbc.queryForList(sql);
        Iterator it = rows.iterator();
        while (it.hasNext()){
            ArrayList<Object> list = new ArrayList<>();
            ConcurrentHashMap<String, ArrayList<Object>> map = new ConcurrentHashMap<>();
            Map data = (Map) it.next();
            BigDecimal keyB = (BigDecimal) data.get("key");
            String key =keyB.toString();
            Object column2 = data.get("column_2");
            Object column3 = data.get("column_3");
            Object column4 = data.get("column_4");
            Object column5 = data.get("column_5");
            Object column6 = data.get("column_6");
            Object column7 = data.get("column_7");
            Object column8 = data.get("column_8");
            Object column9 = data.get("column_9");
            Object column10 = data.get("column_10");
            Object column11 = data.get("column_11");
            Object column12 = data.get("column_12");
            Object column13 = data.get("column_13");
            Object column14 = data.get("column_14");
            Object column15 = data.get("column_15");
            list.add(column2);
            list.add(column3);
            list.add(column4);
            list.add(column5);
            list.add(column6);
            list.add(column7);
            list.add(column8);
            list.add(column9);
            list.add(column10);
            list.add(column11);
            list.add(column12);
            list.add(column13);
            list.add(column14);
            list.add(column15);
             map.put(key,list);
            listMaps.add(map);
        }
    return listMaps;
    }
}
