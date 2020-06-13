package com.study.task.springaop.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)//定义生命周期
@Target(ElementType.METHOD)  //该注解作用于修饰方法
@Documented                   //表明这个注解应该呗javadoc工具记录下来
public @interface EagleEye {
    /**
     *  接口描述
     * @return
     */
    String desc() default "";
}
