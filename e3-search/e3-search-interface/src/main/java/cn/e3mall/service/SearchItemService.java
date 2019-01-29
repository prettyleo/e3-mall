package cn.e3mall.service;

import cn.e3mall.util.E3Result;

/**
 * FileName: SearchItemService
 * DESCRIPTION:
 *
 * @author: SLY
 * @create: 2019/1/28
 */
public interface SearchItemService {

    /**
     * @Description: 将商品信息导入solr索引库
     * @param:
     * @Return: cn.e3mall.util.E3Result
     * @Author: SLY
     * @Date:   2019/1/28 21:53
     */
    E3Result importAllItems();
}
