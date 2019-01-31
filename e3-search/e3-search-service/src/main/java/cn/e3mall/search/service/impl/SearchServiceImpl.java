package cn.e3mall.search.service.impl;

import cn.e3mall.search.dao.SearchDao;
import cn.e3mall.service.SearchService;
import cn.e3mall.vo.resp.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FileName: SearchServiceImpl
 * DESCRIPTION: solr全文检索
 *
 * @author: SLY
 * @create: 2019/1/30
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchDao searchDao;

    /**
     * @Description: 搜索商品
     * @param:  page 页数
     * @param:  rows 每页条数
     * @param:  keyword 搜索关键字
     * @Return: cn.e3mall.vo.resp.SearchResult
     * @Author: SLY
     * @Date:   2019/1/30 22:08
     */
    @Override
    public SearchResult solrSearch(int page, int rows, String keyword) {
        // 创建SolrQuery对象, 并设置查询条件
        SolrQuery query = new SolrQuery();
        if (page < 0) {
            page = 1;
        }
        query.setStart((page - 1) * rows);
        query.setQuery(keyword);
        query.setRows(rows);
        query.set("df", "item_title");
        query.setHighlight(true);
        query.setHighlightSimplePre("<em style=\"color:red\">");
        query.setHighlightSimplePost("</em>");

        // 执行查询
        SearchResult searchResult = searchDao.search(query);

        // 计算页数, 获取总记录数
        long recordCount = searchResult.getRecordCount();
        int pages = (int) (recordCount/rows);
        if (recordCount%rows > 0) {
            pages++;
        }
        searchResult.setTotalPages(page);

        return searchResult;
    }
}
