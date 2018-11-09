package com.shsxt.xmjf.controller;

import com.shsxt.xmjf.api.constant.XmjfConstant;
import com.shsxt.xmjf.api.excepton.ParamsException;
import com.shsxt.xmjf.api.model.ResultInfo;
import com.shsxt.xmjf.api.service.ISmsService;
import com.shsxt.xmjf.api.utils.AssertUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author 康晓伟
 */
@Controller
public class SmsController {
    @Resource
    private ISmsService smsService;

    @RequestMapping("sms")
    @ResponseBody
    public ResultInfo sendSms(String phone, String imageCode, Integer type, HttpSession session){
        ResultInfo resultInfo=new ResultInfo();
        try {
            AssertUtils.isTrue(StringUtils.isBlank(imageCode),"请输入图片验证码!");
            String sessionImageCode= (String) session.getAttribute(XmjfConstant.SESSION_IMAGE);
            AssertUtils.isTrue(StringUtils.isBlank(sessionImageCode),"当前页面已失效,请重新刷新页面!");
            AssertUtils.isTrue(!(imageCode.equals(sessionImageCode)),"图片验证码不正确!");
            session.removeAttribute(XmjfConstant.SESSION_IMAGE);
            smsService.sendSmsCode(phone,type);
        } catch (ParamsException e) {
            e.printStackTrace();
            resultInfo.setCode(e.getCode());
            resultInfo.setMsg(e.getMsg());
        }catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(XmjfConstant.OPS_FAILED_CODE);
            resultInfo.setMsg(XmjfConstant.OPS_FAILED_MSG);
        }
        return resultInfo;
    }
}
