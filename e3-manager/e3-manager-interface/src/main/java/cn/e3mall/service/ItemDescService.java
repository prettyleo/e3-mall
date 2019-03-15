package cn.e3mall.service;

import cn.e3mall.entity.model.TbItemDesc;

/**
 * FileName: ItemDescService
 * DESCRIPTION: 商品描述服务类
 *
 * @author: SLY
 * @create: 2019/2/4
 */
public interface ItemDescService {

    /**
     * @Description: 根据商品id获取商品描述
     * @param:  itemId
     * @Return: cn.e3mall.entity.model.TbItemDesc
     * @Author: SLY
     * @Date:   2019/2/4 17:13
     */
    TbItemDesc getItemDescByItemId(Long itemId);
}
