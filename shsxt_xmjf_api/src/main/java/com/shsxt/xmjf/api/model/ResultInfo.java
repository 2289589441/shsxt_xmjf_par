package com.shsxt.xmjf.api.model;

import java.io.Serializable;

/**
 * @auther: 康晓伟
 * @date: 2018/11/07 19:33
 * @description: ssm_par
 */
public class ResultInfo implements Serializable {
    private static final long serialVersionUID = -3151887406463925502L;

    private int code = 200;
    private String msg = "操作成功";

    @Override
    public String toString() {
        return "ResultInfo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
