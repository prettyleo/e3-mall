package cn.e3mall.vo.resp;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * FileName: ItemListResp
 * DESCRIPTION:
 *
 * @author: Liyou Shen
 * @create: 2019/1/13
 */
@Data
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 8939417085648620238L;
    private Long total;
    private List<T> rows;

}
