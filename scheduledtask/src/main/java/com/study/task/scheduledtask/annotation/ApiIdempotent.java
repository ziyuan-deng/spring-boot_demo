package com.study.task.scheduledtask.annotation;

import java.lang.annotation.*;

/**
 * @author ziyuan_deng
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiIdempotent {
}
