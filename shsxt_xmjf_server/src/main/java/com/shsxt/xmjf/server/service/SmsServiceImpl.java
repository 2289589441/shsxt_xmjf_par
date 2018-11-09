package com.shsxt.xmjf.server.service;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.shsxt.xmjf.api.constant.XmjfConstant;
import com.shsxt.xmjf.api.service.ISmsService;
import com.shsxt.xmjf.api.service.IUserService;
import com.shsxt.xmjf.api.utils.AssertUtils;
import com.shsxt.xmjf.api.utils.PhoneUtil;
import com.shsxt.xmjf.api.utils.RandomCodesUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 康晓伟
 */
@Service
public class SmsServiceImpl implements ISmsService {
    @Resource
    private IUserService userService;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;


    /**
     * 获取验证码
     * @param phone 用户手机号码
     * @param type 验证码类型 1->注册 2->登录
     */
    @Override
    @RequestMapping("sendSmsCode")
    public void sendSmsCode(String phone, Integer type){
        /*
        * 1 校验参数
        *   参数非空校验 | 手机的规则验证 | 手机唯一验证(注册) | type的合法验证(1代表注册 2代表登录)
        * 2 发送验证码(code)
        *   生成验证码  发送验证码
        * 3 存验证码
        *   Redis存储 key:phone+type  value:code
        * */
        checkedParams(phone,type);
        // 发送短信
        String code= RandomCodesUtils.createRandom(true,4);
        System.out.println(code);
        if(type== XmjfConstant.SMS_REGISTER_TYPE){
            AssertUtils.isTrue(null !=userService.queryBasUserByPhone(phone),"该手机号已注册!");
            doSendSms(phone,XmjfConstant.SMS_REGISTER_CODE,code);
        }else if(type==XmjfConstant.SMS_LOGIN_TYPE){
            doSendSms(phone,XmjfConstant.SMS_LOGIN_CODE,code);
        }else if(type==XmjfConstant.SMS_REGISTER_SUCCESS_NOTIFY_TYPE){
            doSendSms(phone,XmjfConstant.SMS_REGISTER_SUCCESS_NOTIFY_CODE,code);
        }else{
            System.out.println("类型不合法!!!");
            return;
        }

        // 加入redis 缓存  失效时间180秒
        // key  value
        String key="phone::"+phone+"::type::"+type;
        redisTemplate.opsForValue().set(key,code,180, TimeUnit.SECONDS);

        /*
         * 模拟Redis存储短信验证码
         */
       // saveSession(phone,code,request);
    }

    /**
     * 模拟Redis存储短信验证码
     * @param code 短信验证码
     * @param request request
     */
    private void saveSession(String phone,String code, HttpServletRequest request) {

    }

    /**
     * 校验参数
     * @param phone 用户手机号码
     * @param type 验证码类型 1->注册 2->登录
     */
    private void checkedParams(String phone, Integer type) {
        //参数非空校验 | 手机的规则验证 | 手机唯一验证(注册) | type的合法验证(1代表注册 2代表登录)
        AssertUtils.isTrue(StringUtils.isBlank(phone),"请输手机号");
        AssertUtils.isTrue(!(PhoneUtil.checkPhone(phone)),"手机号不合法!");

        AssertUtils.isTrue(null==type||!(type== XmjfConstant.SMS_LOGIN_TYPE||type==XmjfConstant.SMS_REGISTER_TYPE||type==XmjfConstant.SMS_REGISTER_SUCCESS_NOTIFY_TYPE),"短信类型不合法!");
    }
    /**
     * 短信发送方法
     * @param phone 用户手机号码
     * @param templateCode 短信类型码
     * @param code 内容
     */
    private void doSendSms(String phone, String templateCode, String code) {
        try {
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");
            //短信API产品名称（短信产品名固定，无需修改）
            final String product = XmjfConstant.SMS_PRODUCT;
            //短信API产品域名（接口地址固定，无需修改）
            final String domain =XmjfConstant.SMS_DOMAIN;
            //你的accessKeyId,参考本文档步骤2
            final String accessKeyId = XmjfConstant.SMS_AK;
            //你的accessKeySecret，参考本文档步骤2
            final String accessKeySecret = XmjfConstant.SMS_SK;
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                    accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            SendSmsRequest request = new SendSmsRequest();
            request.setMethod(MethodType.POST);
            request.setPhoneNumbers(phone);
            request.setSignName(XmjfConstant.SMS_SIGN);
            request.setTemplateCode(templateCode);
            Map<String,String> map= new HashMap<>(1);
            map.put("code",code);
            request.setTemplateParam(JSON.toJSONString(map));
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            AssertUtils.isTrue(!(sendSmsResponse.getCode() != null && "OK".equals(sendSmsResponse.getCode())),"短信发送失败!");
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
