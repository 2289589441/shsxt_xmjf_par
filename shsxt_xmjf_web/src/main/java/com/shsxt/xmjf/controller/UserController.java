package com.shsxt.xmjf.controller;

import com.shsxt.xmjf.api.po.User;
import com.shsxt.xmjf.api.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @auther: 康晓伟
 * @date: 2018/11/07 21:05
 * @description: ssm_par
 */
@Controller
public class UserController {

    @Resource
    private IUserService iUserService;
    @RequestMapping("queryUserByUserId")
    @ResponseBody
    public User queryUserByUserId(Integer userId){
        return iUserService.queryUserByUserId(userId);
    }

}
