package cn.e3mall.controller;

import cn.e3mall.service.ItemCatService;
import cn.e3mall.vo.resp.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * FileName: ItemCatController
 * DESCRIPTION: 商品分类管理
 *
 * @author: Liyou Shen
 * @create: 2019/1/14
 */
@Controller
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    /**
     * 根据节点parentId查询节点
     * @param parentId
     * @return
     */
    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<TreeNode> getItemCatList(@RequestParam(name = "id", defaultValue = "0") Long parentId) {
        return itemCatService.findTreeNodeByParentId(parentId);
    }


}
