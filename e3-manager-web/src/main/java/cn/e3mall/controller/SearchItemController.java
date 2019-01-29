package cn.e3mall.controller;

import cn.e3mall.service.SearchItemService;
import cn.e3mall.util.E3Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * FileName: SearchItemController
 * DESCRIPTION: 导入商品数据
 *
 * @author: SLY
 * @create: 2019/1/28
 */
@Controller
public class SearchItemController {

    @Resource
    private SearchItemService searchItemService;

    @RequestMapping("/index/item/import")
    @ResponseBody
    public E3Result importItemToSolr() {
        return searchItemService.importAllItems();
    }

}
