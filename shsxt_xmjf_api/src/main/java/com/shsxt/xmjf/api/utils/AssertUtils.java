package com.shsxt.xmjf.api.utils;

import com.shsxt.xmjf.api.excepton.ParamsException;

/**
 * @author 康晓伟
 */
public class AssertUtils {
    public static void isTrue(boolean flag, String msg){
        if (flag){
           throw  new ParamsException(msg);
        }
    }
    public static void isTrue(boolean flag, Integer code){
        if (flag){
            throw  new ParamsException(code);
        }
    }
    public static void isTrue(boolean flag, String msg,Integer code){
        if (flag){
            throw  new ParamsException(code,msg);
        }
    }
}
