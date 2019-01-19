package cn.e3mall.service;

import cn.e3mall.vo.resp.TreeNode;

import java.util.List;

public interface ItemCatService {

    /**
     * 根据父节点id查找子节点
     * @param parentId
     * @return
     */
    List<TreeNode> findTreeNodeByParentId(long parentId);
}
