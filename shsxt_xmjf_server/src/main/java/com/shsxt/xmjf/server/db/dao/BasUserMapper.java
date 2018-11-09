package com.shsxt.xmjf.server.db.dao;

import com.shsxt.xmjf.api.po.BasUser;
import com.shsxt.xmjf.api.po.User;
import com.shsxt.xmjf.server.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 康晓伟
 */
public interface BasUserMapper extends BaseMapper<BasUser> {
    /**
     * 根据手机号码 查询用户
     * @param phone 手机号码(唯一)
     * @return 返回user对象
     */
    User queryBasUserByPhone(@Param("phone") String phone);

}