package cn.e3mall.vo.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * FileName: SearchItem
 * DESCRIPTION: 商品搜索查询结果dto
 *
 * @author: SLY
 * @create: 2019/1/26
 */
@Data
public class SearchItem implements Serializable {

    private static final long serialVersionUID = -5768638779187971685L;

    /**
     * 商品id
     */
    private String id;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品卖点
     */
    private String sellPoint;

    /**
     * 商品价格
     */
    private Long price;

    /**
     * 商品图片
     */
    private String image;

    /**
     * 商品分类名称
     */
    private String categoryName;


}
