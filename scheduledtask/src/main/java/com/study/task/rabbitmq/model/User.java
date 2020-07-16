package com.study.task.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户
 *
 * @author ziyuan_deng
 * @create 2020-06-04 22:50
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User implements Serializable {

    private String name;

    private Integer age;

    private String jobNum;
}
