package cn.e3mall.sso.controller;

import cn.e3mall.sso.service.TokenService;
import cn.e3mall.util.E3Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * FileName: TokenController
 * DESCRIPTION:
 *
 * @author: SLY
 * @create: 2019/2/8
 */
@Controller
public class TokenController {

    @Resource
    private TokenService tokenService;

    /**
     * @Description: 通过token获取用户信息-方法一
     * @param:  token
     * @Return: cn.e3mall.util.E3Result
     * @Author: SLY
     * @Date:   2019/2/8 16:27
     */
//    @RequestMapping(value = "/user/token/{token}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ResponseBody
//    public String getUserByToken(@PathVariable("token") String token, String callback) {
//        // callback的jsonp请求, 用于处理js跨域
//        E3Result e3Result = tokenService.getUserByToken(token);
//        if (StringUtils.isNotBlank(callback)) {
//            return callback + "(" + JSON.toJSONString(e3Result) + ")";
//        }
//        return JSON.toJSONString(e3Result);
//    }


    /**
     * @Description: 通过token获取用户信息--方法二(适用于Spring 4.0以上)
     * @param:  token
     * @param:  callback
     * @Return: java.lang.String
     * @Author: SLY
     * @Date:   2019/2/8 17:45
     */
    @RequestMapping(value = "/user/token/{token}")
    @ResponseBody
    public Object getUserByToken(@PathVariable("token") String token, String callback) {
        // callback的jsonp请求, 用于处理js跨域
        E3Result e3Result = tokenService.getUserByToken(token);
        if (StringUtils.isNotBlank(callback)) {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(e3Result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }
        return e3Result;
    }

}
