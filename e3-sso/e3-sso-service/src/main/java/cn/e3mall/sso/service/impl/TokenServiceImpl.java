package cn.e3mall.sso.service.impl;

import cn.e3mall.entity.model.TbUser;
import cn.e3mall.jedis.JedisClient;
import cn.e3mall.sso.service.TokenService;
import cn.e3mall.util.E3Result;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * FileName: TokenServiceImpl
 * DESCRIPTION: Token Service实现
 *
 * @author: SLY
 * @create: 2019/2/8
 */
@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private JedisClient jedisClient;
    @Value("${TOKEN_EXPIRE}")
    private Integer tokenExpire;

    /**
     * @Description: 通过token获取用户信息
     * @param:  token
     * @Return: cn.e3mall.util.E3Result
     * @Author: SLY
     * @Date:   2019/2/8 16:19
     */
    @Override
    public E3Result getUserByToken(String token) {
        // 从redis查询token对应的值
        String json = jedisClient.get(token);
        if (StringUtils.isBlank(json)) {
            return E3Result.build(400, "登录已过期");
        }
        // 重新设置token过期时间
        jedisClient.expire(token, tokenExpire);

        // 返回
        TbUser user = JSON.parseObject(json, TbUser.class);
        return E3Result.ok(user);
    }
}
