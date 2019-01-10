package cn.e3mall.controller;

import cn.e3mall.entity.model.TbItem;
import cn.e3mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * FileName: ItemController
 * DESCRIPTION: 商品管理controller
 *
 * @author: Liyou Shen
 * @create: 2019/1/9
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable("itemId") Long itemId) {
        return itemService.getItemById(itemId);
    }


}
