package cn.e3mall.sso.service;

import cn.e3mall.util.E3Result;

/**
 * FileName: TokenService
 * DESCRIPTION: token Service
 *
 * @author: SLY
 * @create: 2019/2/8
 */
public interface TokenService {

    /**
     * @Description: 通过token获取用户信息
     * @param:  token
     * @Return: cn.e3mall.util.E3Result
     * @Author: SLY
     * @Date:   2019/2/8 16:19
     */
    E3Result getUserByToken(String token);
}
