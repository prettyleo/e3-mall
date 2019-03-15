package cn.e3mall.sso.service;

import cn.e3mall.entity.model.TbUser;
import cn.e3mall.util.E3Result;

/**
 * FileName: service
 * DESCRIPTION: 登录处理service
 *
 * @author: SLY
 * @create: 2019/2/7
 */
public interface RegisterService {

    /**
     * @Description: 校验用户名,邮箱,手机号码是否重复
     * @param:  param 参数
     * @param:  type 参数类型 : 1-用户名/2-手机/3-邮箱
     * @Return: cn.e3mall.util.E3Result
     * @Author: SLY
     * @Date:   2019/2/7 11:09
     */
    E3Result checkParam(String param, int type);

    /**
     * @Description: 用户注册
     * @param:  req 注册表单信息
     * @Return: cn.e3mall.util.E3Result
     * @Author: SLY
     * @Date:   2019/2/7 14:33
     */
    E3Result registry(TbUser req);

}
