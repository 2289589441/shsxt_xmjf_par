package com.shsxt.xmjf.server.db.dao;


import com.shsxt.xmjf.api.po.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * @author 康晓伟
 */
@Repository
public interface UserDao {
    /**
     * 简单模拟查询对象
     * @param userId id
     * @return user
     */
    User queryUserByUserId(Integer userId);

    /**
     * 根据手机号码 查询用户
     * @param phone 手机号码(唯一)
     * @return 返回user对象
     */
    User queryBasUserByPhone(@Param("phone") String phone);
}
