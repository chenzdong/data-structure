package com.czd.bloomfilter.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

/**
 * redis工具类
 *
 * @author: czd
 * @create: 2018/12/28 14:24
 */
public class JedisUtil {
    @Autowired
    RedisTemplate<String,Object> template;
    @Resource(name="template")
    ValueOperations<String,Object> valueOperations;
    public String getStr(String key){
        return (String)valueOperations.get(key);
    }
    public void set(String key,Object value){
        valueOperations.set(key,value);
    }
    public void del(String key){
        template.delete(key);
    }
}
