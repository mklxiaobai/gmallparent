package com.atguigu.gmall.controller;

import com.atguigu.gmall.config.SsoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class HelloController {

    @Autowired
    SsoConfig ssoConfig;

    /**
     * 受保护
     * 1、sso服务器登录成了会在url后面给我们带一个cookie
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(Model model,
                        @CookieValue(value = "sso_user",required = false)
                                String ssoUserCookie,
                        @RequestParam(value = "sso_user",required = false) String ssoUserParam,
                        HttpServletRequest request,
                        HttpServletResponse response) throws IOException {
        /**
         * 我们不要把无意义的uuid放进去。我们把token制作成jwt；
         * header.claims.sign
         */
        if(!StringUtils.isEmpty(ssoUserParam)){
            //没有调用认证服务器登录后跳转回来,说明远程登录了。
            Cookie sso_user = new Cookie("sso_user", ssoUserParam);
            response.addCookie(sso_user);


            return "index";
        }
        StringBuffer requestURL = request.getRequestURL();

        //1、判断是否登录了
        if(StringUtils.isEmpty(ssoUserCookie)){
            //没登录，重定向到登录服务器
            System.out.println("判断进来了？？？");
            //xxxxx?redirec_url=
            //redirect:/重定向到绝对路径
            String url = ssoConfig.getUrl()+ssoConfig.getLoginpath()+"?redirec_url="+requestURL.toString();
            response.sendRedirect(url);
            //return "redirect://"+
            return null;
        }else{
            //登录了，redis.get(ssoUserCookie)获取到用户信息，
            model.addAttribute("loginUser","张三");
            return "index";
        }


    }
}
