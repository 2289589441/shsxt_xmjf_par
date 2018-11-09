package com.shsxt.xmjf.api.service;

/**
 * @auther: 康晓伟
 * @date: 2018/11/09 21:18
 * @description: shsxt_xmjf_par
 */
public interface ISmsService {
    void sendSmsCode(String phone, Integer type);
}
