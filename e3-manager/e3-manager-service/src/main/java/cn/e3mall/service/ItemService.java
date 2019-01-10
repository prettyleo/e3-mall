package cn.e3mall.service;

import cn.e3mall.entity.model.TbItem;

/**
 * 商品管理service
 */
public interface ItemService {

    TbItem getItemById(Long itemId);
}
