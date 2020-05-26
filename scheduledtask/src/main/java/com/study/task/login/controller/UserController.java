package com.study.task.login.controller;

import com.study.task.login.model.SysMenuEntity;
import com.study.task.login.security.model.SelfUserEntity;
import com.study.task.login.service.SysMenuService;
import com.study.task.login.utils.ResultUtil;
import com.study.task.scheduledtask.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 普通用户
 * @Author Sans
 * @CreateTime 2019/10/2 14:43
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 用户端信息
     * @Author Sans
     * @CreateTime 2019/10/2 14:52
     * @Return Map<String,Object> 返回数据MAP
     */
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public Map<String,Object> userLogin(){
        Map<String,Object> result = new HashMap<>();
        SelfUserEntity userDetails = (SelfUserEntity) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        result.put("title","用户端信息");
        result.put("data",userDetails);
        redisUtil.set("orderId_"+ UUID.randomUUID().toString(),"orderInfo",3L);
        return ResultUtil.resultSuccess(result);
    }

    /**
     * 拥有USER角色和sys:user:info权限可以访问
     * @Author Sans
     * @CreateTime 2019/10/2 14:22
     * @Return Map<String,Object> 返回数据MAP
     */
    @PreAuthorize("hasRole('USER') and hasPermission('/user/menuList','sys:user:info')")
    @RequestMapping(value = "/menuList",method = RequestMethod.GET)
    public Map<String,Object> sysMenuEntity(){
        Map<String,Object> result = new HashMap<>();
        List<SysMenuEntity> sysMenuEntityList = sysMenuService.list();
        result.put("title","拥有USER角色和sys:user:info权限可以访问");
        result.put("data",sysMenuEntityList);
        return ResultUtil.resultSuccess(result);
    }

}
