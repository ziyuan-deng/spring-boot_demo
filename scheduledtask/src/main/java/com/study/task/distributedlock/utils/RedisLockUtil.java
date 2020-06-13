package com.study.task.distributedlock.utils;

import com.study.task.distributedlock.lockService.DistributedLocker;
import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * redis分布式锁帮助类
 *
 * @author ziyuan_deng
 * @create 2020-05-26 16:45
 */
public class RedisLockUtil {

    private static DistributedLocker distributedLocker = SpringContextHolder.getBean("distributedLocker", DistributedLocker.class);

    /**
     * 加锁
     * @param lockKey
     * @return
     */
    public static RLock lock(String lockKey){
        return  distributedLocker.lock(lockKey);
    }


    /**
     * 释放锁
     * @param lockKey
     */
     public static void unlock(String lockKey){
         distributedLocker.unlock(lockKey);
     }

    /**
     * 释放锁
     * @param lock
     */
    public static void unlock(RLock lock){
        distributedLocker.unlock(lock);
    }

    /**
     * 带超时的锁
     * @param lockKey
     * @param timeout 超时时间   单位：秒
     */
     public static RLock lock(String lockKey,int timeout){
         return  distributedLocker.lock(lockKey,timeout);
     }

    /**
     * 带超时的锁
     * @param lockKey
     * @param unit 时间单位
     * @param timeout 超时时间
     */
    public static RLock lock(String lockKey,TimeUnit unit,int timeout){
        return  distributedLocker.lock(lockKey,unit,timeout);
    }

    /**
     * 尝试获取锁
     * @param lockKey
     * @param waitTime 最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return
     */
    public static boolean tryLock(String lockKey,int waitTime,int leaseTime ){
        return distributedLocker.tryLock(lockKey,TimeUnit.SECONDS,waitTime,leaseTime);
    }

    /**
     * 尝试获取锁
     * @param lockKey
     * @param unit 时间单位
     * @param waitTime 最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return
     */
     public static boolean tryLock(String lockKey,TimeUnit unit,int waitTime,int leaseTime){
         return distributedLocker.tryLock(lockKey,unit,waitTime,leaseTime);
     }

    /**
     * 获取计数器
     *
     * @param name
     * @return
     */


    /**
     * 获取信号量
     *
     * @param name
     * @return
     */

}
