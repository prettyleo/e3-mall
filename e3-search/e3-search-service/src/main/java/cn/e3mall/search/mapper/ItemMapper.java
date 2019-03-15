package cn.e3mall.search.mapper;

import cn.e3mall.vo.resp.SearchItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * FileName: ItemMapper
 * DESCRIPTION: 商品查询mapper
 *
 * @author: SLY
 * @create: 2019/1/26
 */
public interface ItemMapper {

    /**
     * @Description:  同步mysql数据库数据到solr
     * @param:
     * @Return: java.util.List<cn.e3mall.vo.resp.SearchItem>
     * @Author: SLY
     * @Date:   2019/1/26 22:36
     */
    List<SearchItem> getItemList();

    SearchItem getSearchItemByItemId(@Param("itemId") Long itemId);
}
