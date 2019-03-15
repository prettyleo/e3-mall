package cn.e3mall.sso.service.impl;

import cn.e3mall.entity.model.TbUser;
import cn.e3mall.jedis.JedisClient;
import cn.e3mall.mapper.UserMapper;
import cn.e3mall.sso.service.LoginService;
import cn.e3mall.util.E3Result;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * FileName: LoginServiceImpl
 * DESCRIPTION: 登录Service实现类
 *
 * @author: SLY
 * @create: 2019/2/8
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserMapper userMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${TOKEN_PREFIX}")
    private String TOKEN_PREFIX;
    @Value("${TOKEN_EXPIRE}")
    private Integer TOKEN_EXPIRE;


    /**
     * @Description: 用户登录
     * @param:  username
     * @param:  password
     * @Return: cn.e3mall.util.E3Result
     * @Author: SLY
     * @Date:   2019/2/8 9:16
     */
    @Override
    public E3Result login(String username, String password) {
        // 根据用户名查询获取TbUser
        TbUser query = new TbUser();
        query.setUsername(username);
        TbUser user = userMapper.selectOne(query);

        // 判断用户名是否存在
        if (user == null) {
            return E3Result.build(400, "用户名或密码错误");
        }

        // 若用户名存在, 判断密码是否正确
        if (!user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
            return E3Result.build(400, "用户名或密码错误");
        }

        // 生成token, 存入redis, 设置30分钟过期时间
        String token = TOKEN_PREFIX + UUID.randomUUID().toString();
        jedisClient.set(token, JSON.toJSONString(user));
        jedisClient.expire(token, TOKEN_EXPIRE);

        // 返回结果, 同时传token用于种cookie
        return E3Result.ok(token);
    }
}
