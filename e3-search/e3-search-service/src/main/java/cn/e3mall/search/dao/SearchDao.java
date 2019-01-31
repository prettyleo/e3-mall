package cn.e3mall.search.dao;

import cn.e3mall.vo.resp.SearchItem;
import cn.e3mall.vo.resp.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * FileName: SearchDao
 * DESCRIPTION: solr全文检索封装
 *
 * @author: SLY
 * @create: 2019/1/29
 */
@Repository
@Slf4j
public class SearchDao {

    @Autowired
    private SolrServer solrServer;

    /**
     * @Description: solr执行查询
     * @param:  query 查询条件
     * @Return: cn.e3mall.vo.resp.SearchResult
     * @Author: SLY
     * @Date:   2019/1/30 0:08
     */
    public SearchResult search(SolrQuery query)  {
        // 定义响应对象
        SearchResult result = new SearchResult();
        try {
            // 执行查询
            QueryResponse response = solrServer.query(query);

            // 获取普通结果
            SolrDocumentList resultList = response.getResults();

            // 获取高亮结果
            Map<String, Map<String, List<String>>> highlightingResultList = response.getHighlighting();

            // 定义响应对象中的集合对象
            List<SearchItem> itemList = new ArrayList<>();

            for (SolrDocument document : resultList) {
                SearchItem item = new SearchItem();
                item.setId((String) document.get("id"));
                item.setCategoryName((String) document.get("item_category_name"));
                item.setImage((String) document.get("item_image"));
                item.setPrice((Long) document.get("item_price"));
                item.setSellPoint((String) document.get("item_sell_point"));
                // 获取高亮的title, 如果没有就用普通的title
                List<String> title = highlightingResultList.get(document.get("id")).get("item_title");
                if (!ObjectUtils.isEmpty(title)) {
                    item.setTitle(title.get(0));
                } else {
                    item.setTitle((String) document.get("item_title"));
                }
                itemList.add(item);
            }
            result.setRecordCount(resultList.getNumFound());
            result.setItemList(itemList);
        } catch (SolrServerException e) {
            log.error("solr查询异常:{}", e);
        }
        return result;
    }

}
