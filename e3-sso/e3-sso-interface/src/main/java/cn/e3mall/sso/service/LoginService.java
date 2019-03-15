package cn.e3mall.sso.service;

import cn.e3mall.util.E3Result;

/**
 * FileName: LoginService
 * DESCRIPTION: 登录Service
 *
 * @author: SLY
 * @create: 2019/2/8
 */
public interface LoginService {

    /**
     * @Description: 用户登录
     * @param:  username
     * @param:  password
     * @Return: cn.e3mall.util.E3Result
     * @Author: SLY
     * @Date:   2019/2/8 9:16
     */
    E3Result login(String username, String password);
}
