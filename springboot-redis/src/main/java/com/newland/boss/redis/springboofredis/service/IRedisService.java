package com.newland.boss.redis.springboofredis.service;

import java.util.List;
import java.util.Map;

/**
 * @author ${linfengpeng}
 * @Title: IRedisService
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2020/11/4 15:54
 */
public interface IRedisService {
    // 加入元素
    void setValue(String key, Map<String, Object> value);
    // 加入元素
    void setValue(String key, String value);
    // 加入元素
    void setValue(String key, Object value);
    // 获取元素
    Object getMapValue(String key);
    // 获取元素
    Object getValue(String key);
}
