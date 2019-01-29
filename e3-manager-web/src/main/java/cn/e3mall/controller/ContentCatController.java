package cn.e3mall.controller;

import cn.e3mall.content.service.ContentCatService;
import cn.e3mall.util.E3Result;
import cn.e3mall.vo.resp.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * FileName: ContentCatController
 * DESCRIPTION: 商品分类管理器
 *
 * @author: SLY
 * @create: 2019/1/19
 */
@Controller
public class ContentCatController {

    @Autowired
    private ContentCatService contentCatService;

    @RequestMapping("/content/category/list")
    @ResponseBody
    public List<TreeNode> getCategoryList(@RequestParam(name = "id", defaultValue = "0") Long parentId) {
        return contentCatService.getContentCategoryList(parentId);
    }


    /**
     * @Description: 添加商品分类节点
     * @param parentId 父级ID
     * @param name 添加节点的名称
     * @Return: cn.e3mall.util.E3Result
     * @Author: SLY
     * @Date: 2019/1/19 18:58
     */
    @RequestMapping(value = "/content/category/create", method = RequestMethod.POST)
    @ResponseBody
    public E3Result createCategory(Long parentId, String name) {
        return contentCatService.addContentCategory(parentId, name);
    }




}
