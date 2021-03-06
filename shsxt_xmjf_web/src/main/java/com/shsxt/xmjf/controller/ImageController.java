package com.shsxt.xmjf.controller;

import com.google.code.kaptcha.Producer;
import com.shsxt.xmjf.api.constant.XmjfConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author 康晓伟
 * @auther: 康晓伟
 * @date: 2018/11/08 20:38
 * @description: shsxt_xmjf_par
 */
@Controller
public class ImageController {
    @Resource
    private Producer producer;

    @RequestMapping("image")
    public void image(HttpServletRequest request, HttpServletResponse response){
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");
        // 图片验证码值
        String code=producer.createText();
        System.out.println("验证码值:"+code);
        BufferedImage bi= producer.createImage(code);
        ServletOutputStream sos=null;
        try {
            sos=response.getOutputStream();
            // 存储验证码到当前会话
            request.getSession().setAttribute(XmjfConstant.SESSION_IMAGE,code);
            ImageIO.write(bi,"jpg",sos);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null !=sos){
                try {
                    sos.flush();
                    sos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
