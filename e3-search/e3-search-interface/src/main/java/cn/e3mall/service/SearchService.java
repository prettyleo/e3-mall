package cn.e3mall.service;

import cn.e3mall.vo.resp.SearchResult;

/**
 * FileName: SearchService
 * DESCRIPTION: solr全文检索service
 *
 * @author: SLY
 * @create: 2019/1/30
 */
public interface SearchService {

    /**
     * @Description: 搜索商品
     * @param:  page 页数
     * @param:  rows 每页条数
     * @param:  keyword 搜索关键字
     * @Return: cn.e3mall.vo.resp.SearchResult
     * @Author: SLY
     * @Date:   2019/1/30 22:08
     */
    SearchResult solrSearch(int page, int rows, String keyword);
}
