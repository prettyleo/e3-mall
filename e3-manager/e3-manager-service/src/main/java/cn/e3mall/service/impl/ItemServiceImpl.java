package cn.e3mall.service.impl;

import cn.e3mall.entity.model.TbItem;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.service.ItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * FileName: ItemServiceImpl
 * DESCRIPTION: 商品管理实现类
 *
 * @author: Liyou Shen
 * @create: 2019/1/9
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    private TbItemMapper tbItemMapper;

    @Override
    public TbItem getItemById(long itemId) {

        return tbItemMapper.selectByPrimaryKey(itemId);
    }
}
