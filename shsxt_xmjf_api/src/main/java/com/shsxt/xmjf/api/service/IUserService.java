package com.shsxt.xmjf.api.service;

import com.shsxt.xmjf.api.po.User;

/**
 * @author 康晓伟
 */
public interface IUserService {
    /**
     * 简单模拟查询用户
     * @param userId id
     * @return user
     */
    User queryUserByUserId(Integer userId);

    /**
     * 根据手机号码 查询用户
     * @param phone 手机号码(唯一)
     * @return 返回user对象
     */
    User queryBasUserByPhone(String phone);
}
