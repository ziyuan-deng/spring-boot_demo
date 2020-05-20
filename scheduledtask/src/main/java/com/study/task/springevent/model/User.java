package com.study.task.springevent.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户信息
 *
 * @author ziyuan_deng
 * @create 2020-05-11 23:08
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private String userId;

    private String name;

    private String email;

    private String phone;

    private String address;

    private Integer age;


}
