package cn.e3mall.controller;

import cn.e3mall.entity.model.TbItem;
import cn.e3mall.service.ItemService;
import cn.e3mall.vo.resp.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * FileName: ItemController
 * DESCRIPTION: 商品管理controller
 *
 * @author: Liyou Shen
 * @create: 2019/1/9
 */
@Slf4j
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable("itemId") Long itemId) {
        log.info("getItemById请求开始");
        return itemService.getItemById(itemId);
    }

    /**
     * 查询商品列表
     * @param page 第几页
     * @param rows 每页条数
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageResult getList(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows ) {
        return itemService.getItemList(page, rows);
    }

}
