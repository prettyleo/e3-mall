package cn.e3mall.sso.controller;

import cn.e3mall.entity.model.TbUser;
import cn.e3mall.sso.service.RegisterService;
import cn.e3mall.util.E3Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * FileName: controller
 * DESCRIPTION: 单点登录控制器
 *
 * @author: SLY
 * @create: 2019/2/6
 */
@Controller
public class RegistrationController {

    @Autowired
    private RegisterService registerService;

    @RequestMapping("/page/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/user/check/{param}/{type}")
    @ResponseBody
    public E3Result checkData(@PathVariable("param") String param, @PathVariable("type") int type) {
        return registerService.checkParam(param, type);
    }

    /**
     * @Description: 注册
     * @param:  req
     * @Return: cn.e3mall.util.E3Result
     * @Author: SLY
     * @Date:   2019/2/7 15:00
     */
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    @ResponseBody
    public E3Result register(TbUser req) {
        return registerService.registry(req);
    }

}
