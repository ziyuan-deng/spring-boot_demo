package com.study.task.scheduledtask.utils;

import org.springframework.data.redis.core.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class RedisUtil {

    private RedisTemplate<Serializable,Object> redisTemplate;

    public RedisUtil(RedisTemplate<Serializable,Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    /**
     *
     * 批量删除对应的value
     * @param keys
     */
    public void remove(String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(String key) {
        if (exists(key)) {
            Boolean delete = redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(String key) {
    	Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    public Collection<Object> getPattern(String pattern){
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if(keys.size() == 0){
            return  null;
        }

        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        return operations.multiGet(keys);
    }

    /**
     * 根据匹配条件获取所有的key
     * @param pattern
     * @return
     */
    public  Set<Serializable> getKeys(String pattern){
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if(keys.size() == 0){
            return  null;
        }else {
            return keys;
        }

    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入hash表
     * @param key
     * @param value
     */
    @SuppressWarnings("unchecked")
	public void setHash(String key , Map<String,?> value){
        @SuppressWarnings("rawtypes")
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.putAll(key,value);
    }

    /**
     * 从hash表获取数据
     * @param key
     * @return
     */
    public Object getHash(String key){
        return redisTemplate.opsForHash().entries(key);
    }


    /**
     * 删除hash表数据
     * @param key
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public  void deleteHash(String key){
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.getOperations().delete(key);
    }

    /**
     * 写入list
     * @param key
     * @param value
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void setList(String key , List<?> value){
        //ListOperations可以理解为List<Object>
        ListOperations listOperations= redisTemplate.opsForList();
        listOperations.leftPush(key, value);
//                .leftPushAll(value);
    }

    /**
     * 删除list
     * @param key
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteList(String key){
        ListOperations listOperations= redisTemplate.opsForList();
        listOperations.getOperations().delete(key);
    }

    /**
     * 获取list
     * @param key
     * @return
     */
    public Object getList(String key){
        //ListOperations可以理解为List<Object>
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 写入set
     * @param key
     * @param value
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void setSet(String key , Set<?> value){
        SetOperations setOperations= redisTemplate.opsForSet();
        setOperations.add(key, value);
    }

    /**
     * 获取set
     * @param key
     * @return
     */
    public Object getSet(String key){
        return redisTemplate.opsForSet().members(key);
    }


    /**
     * 删除set
     * @param key
     */
    @SuppressWarnings("unchecked")
	public void deleteSet(String key){
        @SuppressWarnings("rawtypes")
        SetOperations setOperations= redisTemplate.opsForSet();
        setOperations.getOperations().delete(key);
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @param expireTime 缓存时间,单位秒
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 设定缓存的失效时间
     * @param key
     * @param expireTime
     * @return
     */
    public boolean expire(final String key, Long expireTime) {
        boolean result = false;
        try {
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
