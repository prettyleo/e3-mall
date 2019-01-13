package cn.e3mall.service;

import cn.e3mall.entity.model.TbItem;
import cn.e3mall.vo.resp.PageResult;

/**
 * 商品管理service
 */
public interface ItemService {

    /**
     * 测试, 根据itemId查询对应TbItem
     * @param itemId
     * @return
     */
    TbItem getItemById(Long itemId);

    /**
     * 查询商品列表, 分页
     * @param page
     * @param rows
     * @return
     */
    PageResult getItemList(Integer page, Integer rows);
}
