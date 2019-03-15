package cn.e3mall.sso.controller;

import cn.e3mall.sso.service.LoginService;
import cn.e3mall.sso.service.TokenService;
import cn.e3mall.util.CookieUtils;
import cn.e3mall.util.E3Result;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * FileName: LoginController
 * DESCRIPTION: 登录
 *
 * @author: SLY
 * @create: 2019/2/7
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private TokenService tokenService;

    @RequestMapping("/page/login")
    public String showLogin() {
        return "login";
    }


    /**
     * @Description: 登录
     * @param:  username
     * @param:  password
     * @param:  request
     * @param:  response
     * @Return: cn.e3mall.util.E3Result
     * @Author: SLY
     * @Date:   2019/2/8 16:26
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public E3Result login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        E3Result e3Result = loginService.login(username, password);
        // 判断是否登录成功, 如果登录成功就写入cookie
        if (e3Result.getStatus() == 200) {
            CookieUtils.setCookie(request, response, "token", e3Result.getData().toString());
        }
        return e3Result;
    }

}
