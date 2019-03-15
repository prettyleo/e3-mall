package cn.e3mall.service;

import cn.e3mall.entity.model.TbItem;
import cn.e3mall.util.E3Result;
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


    /**
     * @Description: 添加商品
     * @param item 其余请求表单内容
     * @param desc 富文本内容
     * @Return: E3Result
     * @Author: SLY
     * @Date: 2019/1/19 10:42
     */
    E3Result addItem(TbItem item, String desc);

}
