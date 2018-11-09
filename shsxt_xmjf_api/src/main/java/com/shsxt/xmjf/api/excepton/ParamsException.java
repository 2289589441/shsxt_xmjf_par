package com.shsxt.xmjf.api.excepton;

import com.shsxt.xmjf.api.constant.XmjfConstant;
/**
 * @author 康晓伟
 */
public class ParamsException extends RuntimeException {
    private Integer code= XmjfConstant.OPS_FAILED_CODE;
    private String  msg=XmjfConstant.OPS_FAILED_MSG;

    public ParamsException() {
        super(XmjfConstant.OPS_FAILED_MSG);
    }

    public ParamsException(Integer code) {
        super(XmjfConstant.OPS_FAILED_MSG);
        this.code = code;
    }

    public ParamsException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ParamsException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }


    @Override
    public String toString() {
        return "BusiException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
