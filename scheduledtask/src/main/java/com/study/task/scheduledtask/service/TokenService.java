package com.study.task.scheduledtask.service;

import org.springframework.web.servlet.function.ServerResponse;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {

   public  String createToken();

   public void  checkToken(HttpServletRequest request);
}
