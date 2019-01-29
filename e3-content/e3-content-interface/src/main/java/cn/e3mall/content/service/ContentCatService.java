package cn.e3mall.content.service;

import cn.e3mall.util.E3Result;
import cn.e3mall.vo.resp.TreeNode;

import java.util.List;

/**
 * FileName: ContentCatService
 * DESCRIPTION: 商品分类service
 *
 * @author: SLY
 * @create: 2019/1/19
 */
public interface ContentCatService {

    /**
     * @Description: 根据父级id查找子节点
     * @param parentId 父级id
     * @Return: java.util.List<cn.e3mall.vo.resp.TreeNode>
     * @Author: SLY
     * @Date: 2019/1/19 15:49
     */
    List<TreeNode> getContentCategoryList(long parentId);

    /**
     * @Description: 添加叶子节点(商品类目名称)
     * @param parentId 父级id
     * @param name 节点名称(商品类目名称)
     * @Return: cn.e3mall.util.E3Result
     * @Author: SLY
     * @Date: 2019/1/19 18:40
     */
    E3Result addContentCategory(long parentId, String name);

}
