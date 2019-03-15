package cn.e3mall.sso.service.impl;

import cn.e3mall.entity.model.TbUser;
import cn.e3mall.mapper.UserMapper;
import cn.e3mall.sso.service.RegisterService;
import cn.e3mall.util.E3Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;

/**
 * FileName: RegisterServiceImpl
 * DESCRIPTION: 登录处理Service实现类
 *
 * @author: SLY
 * @create: 2019/2/7
 */
@Service
@Slf4j
public class RegisterServiceImpl implements RegisterService {

    @Resource
    private UserMapper userMapper;

    /**
     * @Description: 校验用户名,邮箱,手机号码是否重复
     * @param:  param 参数
     * @param:  type 参数类型 : 1-用户名/2-手机/3-邮箱
     * @Return: cn.e3mall.util.E3Result
     * @Author: SLY
     * @Date:   2019/2/7 11:09
     */
    @Override
    public E3Result checkParam(String param, int type) {
        TbUser query = new TbUser();
        switch (type) {
            case 1:
                query.setUsername(param);
                break;
            case 2:
                query.setPhone(param);
                break;
            case 3:
                query.setEmail(param);
                break;
            default:
                return E3Result.build(400, "参数类型错误");
        }
        TbUser user = userMapper.selectOne(query);
        if (user != null) {
            E3Result.ok(false);
        }
        return E3Result.ok(true);
    }

    /**
     * @Description: 用户注册
     * @param:  req 注册表单信息
     * @Return: cn.e3mall.util.E3Result
     * @Author: SLY
     * @Date:   2019/2/7 14:33
     */
    @Override
    public E3Result registry(TbUser req) {
        // 参数非空校验
        if (StringUtils.isBlank(req.getUsername()) || StringUtils.isBlank(req.getPhone())) {
            return E3Result.build(400, "存在参数为空");
        }

        // 参数用户名,手机号重复校验
        boolean isUserNameRepeat = checkData(req.getUsername(), 1);
        boolean isPhoneRepeat = checkData(req.getPhone(), 2);
        if (isUserNameRepeat || isPhoneRepeat) {
            return E3Result.build(400, "用户名或手机或邮箱重复");
        }

        // MD5加密
        String md5Encrypt = DigestUtils.md5DigestAsHex(req.getPassword().getBytes());
        req.setPassword(md5Encrypt);

        // 补全pojo
        req.setCreated(new Date());
        req.setUpdated(new Date());

        // 数据入库
        userMapper.insert(req);

        // 响应
        return E3Result.ok();
    }

    /**
     * @Description: 用户名,手机,邮箱重复校验
     * @param:  param 参数
     * @param:  type 参数类型:1-用户名/2-手机/3-邮箱
     * @Return: boolean
     * @Author: SLY
     * @Date:   2019/2/7 14:46
     */
    private boolean checkData(String param, int type) {
        boolean isRepeat = false;
        TbUser query = new TbUser();
        switch (type) {
            case 1:
                query.setUsername(param);
                break;
            case 2:
                query.setPhone(param);
                break;
            default:
                // 参数类型参数不再范围内, 不做校验。
                return false;
        }
        TbUser user = userMapper.selectOne(query);
        if (user != null) {
            isRepeat = true;
        }
        return isRepeat;
    }
}
