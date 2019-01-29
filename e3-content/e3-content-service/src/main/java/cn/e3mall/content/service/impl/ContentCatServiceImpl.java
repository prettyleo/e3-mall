package cn.e3mall.content.service.impl;

import cn.e3mall.content.service.ContentCatService;
import cn.e3mall.entity.model.TbContentCategory;
import cn.e3mall.enums.CloseStatusEnum;
import cn.e3mall.mapper.ContentCategoryMapper;
import cn.e3mall.util.E3Result;
import cn.e3mall.vo.resp.TreeNode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * FileName: ContentCatServiceImpl
 * DESCRIPTION: 商品分类service实现类
 *
 * @author: SLY
 * @create: 2019/1/19
 */
@Service
public class ContentCatServiceImpl implements ContentCatService {

    @Resource
    private ContentCategoryMapper contentCategoryMapper;


    /**
     * @Description: 根据父级id查找子节点
     * @param parentId 父级id
     * @Return: java.util.List<cn.e3mall.vo.resp.TreeNode>
     * @Author: SLY
     * @Date: 2019/1/19 15:49
     */
    @Override
    public List<TreeNode> getContentCategoryList(long parentId) {
        // 1.根据parentId查询数据
        TbContentCategory category = new TbContentCategory();
        category.setParentId(parentId);
        List<TbContentCategory> categoryList = contentCategoryMapper.select(category);

        // 2.将查询List泛型转为TreeNode
        List<TreeNode> treeNodeList = new ArrayList<>();
        for (TbContentCategory contentCategory : categoryList) {
            TreeNode treeNode = new TreeNode();
            treeNode.setId(contentCategory.getId());
            treeNode.setText(contentCategory.getName());
            treeNode.setState(contentCategory.getIsParent() ? CloseStatusEnum.CLOSED.status : CloseStatusEnum.OPEN.status);
            treeNodeList.add(treeNode);
        }

        return treeNodeList;
    }

    /**
     * @Description: 添加叶子节点(商品类目名称)
     * @param parentId 父级id
     * @param name 节点名称(商品类目名称)
     * @Return: cn.e3mall.util.E3Result
     * @Author: SLY
     * @Date: 2019/1/19 18:40
     */
    @Override
    public E3Result addContentCategory(long parentId, String name) {

        // 1.配置 TbContentCategory 对象
        TbContentCategory category = new TbContentCategory();
        category.setParentId(parentId);
        category.setName(name);
        category.setStatus(1);
        category.setSortOrder(1);
        // 新添加节点一定是叶子节点
        category.setIsParent(false);
        category.setCreated(new Date());
        category.setUpdated(new Date());

        // 2.保存, 返回自增主键
        int insert = contentCategoryMapper.insert(category);

        // 3.判断父级节点是isParent字段是否为false, 如果为false要设为true, 并保存
        TbContentCategory query = new TbContentCategory();
        query.setParentId(parentId);
        TbContentCategory parentCategory = contentCategoryMapper.selectOne(query);
        if (parentCategory != null && !parentCategory.getIsParent()) {
            parentCategory.setIsParent(true);
            contentCategoryMapper.updateByPrimaryKey(parentCategory);
        }

        // 4.响应成功的 E3Result
        return E3Result.ok(category);
    }


}
