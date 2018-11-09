package com.shsxt.xmjf.server.service;

import com.shsxt.xmjf.api.po.User;
import com.shsxt.xmjf.api.service.IUserService;
import com.shsxt.xmjf.server.db.dao.BasUserMapper;
import com.shsxt.xmjf.server.db.dao.UserDao;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @auther: 康晓伟
 * @date: 2018/11/07 19:41
 * @description: ssm_par
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserDao userDao;

    @Resource
    private BasUserMapper basUserMapper;

    @Override
    public User queryUserByUserId(Integer userId) {

        return userDao.queryUserByUserId(userId);
    }

    @Override
    public User queryBasUserByPhone(String phone) {
        return basUserMapper.queryBasUserByPhone(phone);
    }
}
