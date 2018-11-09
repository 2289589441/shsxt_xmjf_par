package com.shsxt.xmjf.api.po;

import java.io.Serializable;

/**
 * @auther: 康晓伟
 * @date: 2018/11/07 19:29
 * @description: ssm_par
 */
public class User implements Serializable {
    private static final long serialVersionUID = -431851354165903552L;

    private Integer id;
    private String userName;
    private String userPwd;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
}
