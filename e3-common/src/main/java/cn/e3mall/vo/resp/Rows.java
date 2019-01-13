package cn.e3mall.vo.resp;

import cn.e3mall.annotation.DiffName;
import lombok.Data;

import java.io.Serializable;

/**
 * FileName: Rows
 * DESCRIPTION:
 *
 * @author: Liyou Shen
 * @create: 2019/1/13
 */
@Data
public class Rows implements Serializable {

    /**
     * 商品ID
     */
    private Long id;

    /**
     * 商品标题
     */
    @DiffName("title")
    private String name;

}
