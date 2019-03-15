package cn.e3mall.controller;

import cn.e3mall.entity.model.TbItem;
import cn.e3mall.entity.model.TbItemDesc;
import cn.e3mall.service.ItemDescService;
import cn.e3mall.service.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pojo.Item;

/**
 * FileName: ItemDetailController
 * DESCRIPTION: 商品详情控制器
 *
 * @author: SLY
 * @create: 2019/2/4
 */
@Controller
public class ItemDetailController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemDescService itemDescService;

    @RequestMapping("/item/{itemId}")
    public String getItemDetail(@PathVariable("itemId")Long itemId, Model model) {
        // 查询商品详情
        TbItem tbItem = itemService.getItemById(itemId);
        // 转为Item对象
        Item item = new Item();
        BeanUtils.copyProperties(tbItem, item);
        // 设置images属性
        item.setImages(item.getImage());

        // 查询商品描述
        TbItemDesc itemDesc = itemDescService.getItemDescByItemId(itemId);

        // 绑定到model
        model.addAttribute("item", item);
        model.addAttribute("itemDesc", itemDesc);

        // 返回逻辑视图
        return "item";
    }



}
