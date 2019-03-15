package cn.e3mall.service.impl;

import cn.e3mall.entity.model.TbItemDesc;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.service.ItemDescService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * FileName: ItemDescServiceImpl
 * DESCRIPTION: 商品描述Service实现
 *
 * @author: SLY
 * @create: 2019/2/4
 */
@Service
public class ItemDescServiceImpl implements ItemDescService {

    @Resource
    private TbItemDescMapper tbItemDescMapper;

    /**
     * @Description: 根据商品id获取商品描述
     * @param:  itemId
     * @Return: cn.e3mall.entity.model.TbItemDesc
     * @Author: SLY
     * @Date:   2019/2/4 17:13
     */
    @Override
    public TbItemDesc getItemDescByItemId(Long itemId) {
        TbItemDesc query = new TbItemDesc();
        query.setItemId(itemId);
        return tbItemDescMapper.selectOne(query);
    }
}
