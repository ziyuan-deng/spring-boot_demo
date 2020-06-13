package com.study.task.distributedlock.controller;

import com.study.task.distributedlock.lockService.DistributedLocker;
import com.study.task.distributedlock.utils.RedisLockUtil;
import com.study.task.distributedlock.utils.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 测试分布式锁
 *
 * @author ziyuan_deng
 * @create 2020-05-26 17:50
 */
@RestController
@RequestMapping("/redisson")
public class RedissonLockController {
    protected static Logger logger = LoggerFactory.getLogger(RedissonLockController.class);
    /**
     * 锁测试共享变量
     */
    private Integer lockCount = 10;

    /**
     * 无锁测试共享变量
     */
    private Integer count = 10;

    /**
     * 模拟线程数
     */
    private static int threadNum = 10;

    /**
     * 模拟测试加锁与不加锁
     */
    @GetMapping("/lock")
    public void lock(){
        DistributedLocker distributedLocker = SpringContextHolder.getBean("distributedLocker", DistributedLocker.class);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i=0; i<threadNum; i++){
            MyRunnable myRunnable = new MyRunnable(countDownLatch);
            Thread myThread = new Thread(myRunnable);
            myThread.start();
        }
        countDownLatch.countDown();
    }

    class MyRunnable implements Runnable {

        final CountDownLatch countDownLatch;

        public MyRunnable(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                // 阻塞当前线程，直到计时器的值为0
                countDownLatch.await();
            }catch (Exception e){
                logger.error(e.getMessage(),e);
            }
            //无锁操作
            testCount();
            //加锁操作
            testLockCount();
        }
    }
    private void testLockCount() {
        String lockKey = "lock-test";

        try {
            RedisLockUtil.lock(lockKey, TimeUnit.SECONDS,2);
            //Thread.sleep(5000);
            lockCount--;
            logger.info("lockCount值：" +lockCount);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }finally {
            //释放锁
            RedisLockUtil.unlock(lockKey);
        }

    }

    private void testCount() {
        count--;
        logger.info("count值：" +count);

    }
}
