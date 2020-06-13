package com.study.task.distributedlock.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取spring上下文工具类
 *
 * @author ziyuan_deng
 * @create 2020-05-26 17:27
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext context ;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
    /**
     * 获取applicationContext
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return context;
    }
    /**
     * 通过name及Clazz返回指定的Bean
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }
    /**
     * 通过name获取 Bean.
     * @param name
     * @return
     */
    public static Object getBean(String name){

        return getApplicationContext().getBean(name);
    }
    /**
     * 通过class获取Bean.
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz){

        return getApplicationContext().getBean(clazz);
    }


}
