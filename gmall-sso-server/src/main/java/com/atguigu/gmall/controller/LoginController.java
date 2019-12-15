package com.atguigu.gmall.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.mkl.gmall.constant.SysCacheConstant;
import com.mkl.gmall.to.CommonResult;
import com.mkl.gmall.ums.entity.Member;
import com.mkl.gmall.ums.service.MemberService;
//import com.mkl.gmall.vo.ums.LoginResponseVo;
import com.mkl.gmall.vo.ums.LoginResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
public class LoginController {


    @Autowired
    StringRedisTemplate redisTemplate;

    @Reference
    MemberService memberService;


    /**
     * 这个方法是我们系统用的登录方法，只需要登录成功统一返回token即可
     * @return
     */
    @ResponseBody
    @PostMapping("/applogin")
    public CommonResult loginForGmall(@RequestParam("username") String username,
                                      @RequestParam("password") String password) {
        System.out.println("--------------");
        Member member = memberService.login(username, password);
        if (member == null) {
            //用户没有
            CommonResult result = new CommonResult().failed();
            result.setMessage("账号密码不匹配，请重新登录");
            return result;
        } else {
            String token = UUID.randomUUID().toString().replace("-", "");
            String memberJson = JSON.toJSONString(member);
            redisTemplate.opsForValue()
                    .set(SysCacheConstant.LOGIN_MEMBER + token, memberJson,
                            SysCacheConstant.LOGIN_MEMBER_TIMEOUT, TimeUnit.MINUTES);

            LoginResponseVo vo = new LoginResponseVo();
            BeanUtils.copyProperties(member, vo);
            //设置访问令牌
            vo.setAccessToken(token);
            return new CommonResult().success(vo);
        }
    }

    @ResponseBody
    @GetMapping("/userinfo")
    public CommonResult getUserInfo(@RequestParam("accessToken") String accessToken){
        //确定去redis中查询用户用的key
        String redisKey= SysCacheConstant.LOGIN_MEMBER+accessToken;
        String member = redisTemplate.opsForValue().get(redisKey);
        Member loginMember = JSON.parseObject(member, Member.class);
        loginMember.setId(null);
        loginMember.setPassword(null);
        return new CommonResult().success(loginMember);
    }
}
