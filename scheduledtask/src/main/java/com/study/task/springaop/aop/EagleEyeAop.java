package com.study.task.springaop.aop;

import com.alibaba.fastjson.JSON;
import com.study.task.distributedlock.lockService.impl.RedisDistributedLocker;
import com.study.task.springaop.annotation.EagleEye;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 登陆信息aop
 *
 * @author ziyuan_deng
 * @create 2020-05-27 15:38
 */
@Aspect
@Component
public class EagleEyeAop {

    protected static Logger logger = LoggerFactory.getLogger(EagleEyeAop.class);
    @Pointcut("@annotation(com.study.task.springaop.annotation.EagleEye)")
    public  void  eagleEye(){
    }

    @Around("eagleEye()")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        long begin = System.currentTimeMillis();
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        EagleEye eagleEye = method.getAnnotation(EagleEye.class);
        String desc = eagleEye.desc();
        logger.info("======请求开始=======");
        logger.info("请求接口描述："+desc);
        logger.info("请求方法：{},{}",signature.getDeclaringTypeName(),((MethodSignature) signature).getMethod());
        logger.info("请求参数：{}", JSON.toJSONString(point.getArgs()));
        Object result = point.proceed();

        long end = System.currentTimeMillis();

        logger.info("请求耗时：{} ms",end - begin);
        logger.info("请求返回：{} ",JSON.toJSONString(result));
        logger.info("================   请求结束=========");
        return result;
    }

}
