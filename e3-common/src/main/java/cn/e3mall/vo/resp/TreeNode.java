package cn.e3mall.vo.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * FileName: TreeNode
 * DESCRIPTION:
 *
 * @author: Liyou Shen
 * @create: 2019/1/13
 */
@Data
public class TreeNode implements Serializable {

    private static final long serialVersionUID = -580809585750402632L;

    /**
     * tb_item_cat的id字段
     */
    private Long id;

    /**
     * tb_item_cat的name字段
     */
    private String text;

    /**
     * 节点关闭状态, 如果有子节点就是"close", 否则"open"
     */
    private String state;
}
