package com.study.task.rabbitmq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * 生成模拟抢单的线程
 *
 * @author ziyuan_deng
 * @create 2020-06-05 11:55
 */
@Service
public class InitThreadService {

    private static final Logger log= LoggerFactory.getLogger(InitThreadService.class);


    @Autowired
    private RobbingMqService robbingMqService;

    public static final int ThreadNum = 500;

    private static int mobile=0;

    public void generateMultiThread(){

        log.info("开始初始化线程数----> ");
        try {
            CountDownLatch latch = new CountDownLatch(1);
            for (int i=0; i<ThreadNum; i++){
                new Thread(new RobbingRunThread(latch)).start();
            }
            latch.countDown();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public class  RobbingRunThread implements  Runnable{

        private CountDownLatch latch;

        public RobbingRunThread(){

        }
        public RobbingRunThread(CountDownLatch latch){
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                latch.await();
                mobile = mobile+1;
                // concurrencyService.manageRobbing(mobile);
                robbingMqService.sendRobbingMsg(mobile);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
