package cn.e3mall.service.impl;

import cn.e3mall.entity.model.TbItemCat;
import cn.e3mall.mapper.ItemCatMapper;
import cn.e3mall.service.ItemCatService;
import cn.e3mall.vo.resp.TreeNode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: ItemCatServiceImpl
 * DESCRIPTION: 商品类目实现类
 *
 * @author: Liyou Shen
 * @create: 2019/1/13
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Resource
    private ItemCatMapper itemCatMapper;

    /**
     * 根据父节点id查找子节点
     * @param parentId 父级节点id
     * @return
     */
    @Override
    public List<TreeNode> findTreeNodeByParentId(long parentId) {
        // 根据parentId查询子节点集合
        TbItemCat query = new TbItemCat();
        query.setParentId(parentId);
        List<TbItemCat> itemCatList = itemCatMapper.select(query);

        // 将集合转为TreeNode泛型
        List<TreeNode> treeNodeList = new ArrayList<>();
        for (TbItemCat itemCat : itemCatList) {
            TreeNode treeNode = new TreeNode();
            treeNode.setId(itemCat.getId());
            treeNode.setText(itemCat.getName());
            treeNode.setState(itemCat.getIsParent() ? "closed" : "open");
            treeNodeList.add(treeNode);
        }

        // 返回结果
        return treeNodeList;
    }



}
