package cn.e3mall.vo.resp;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * FileName: SearchResult
 * DESCRIPTION:
 *
 * @author: SLY
 * @create: 2019/1/29
 */
@Data
public class SearchResult implements Serializable {

    private static final long serialVersionUID = -5585104627434833592L;

    /**
     * 查询总记录数
     */
    private long recordCount;

    /**
     * 总页数
     */
    private int totalPages;

    /**
     * 查询结果集合
     */
    private List<SearchItem> itemList;

}
