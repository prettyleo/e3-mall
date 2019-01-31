package cn.e3mall.search.mapper;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * FileName: SolrTest
 * DESCRIPTION:
 *
 * @author: SLY
 * @create: 2019/1/26
 */
public class SolrTest {

    private static final String DEFAULT = "df";

    @Test
    public void testAddDocumentTOSolr() throws IOException, SolrServerException {
        // 创建solr连接
        SolrServer solrServer = new HttpSolrServer("http://192.168.25.128:8080/solr/collection1");
        // 创建文本对象, 并添加域。其中id域是必加的
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", "01");
        document.addField("item_title", "我是标题");
        document.addField("item_price", 1000);
        // 文档写入索引库
        solrServer.add(document);
        // 提交
        solrServer.commit();
    }

    /**
     * @Description: 删除
     * @param:
     * @Return: void
     * @Author: SLY
     * @Date:   2019/1/28 23:34
     */
    @Test
    public void delSolrDocument() throws IOException, SolrServerException {
        // 建立solr服务器连接
        SolrServer solrServer = new HttpSolrServer("http://192.168.25.128:8080/solr/collection1");
        // 根据业务域id删除文档
        solrServer.deleteById("01");
        //提交
        solrServer.commit();
    }

    /**
     * @Description: solr查询
     * @param:
     * @Return: void
     * @Author: SLY
     * @Date:   2019/1/28 23:35
     */
    @Test
    public void solrSearch() throws SolrServerException {
        // 创建SolrServer对象
        SolrServer solrServer = new HttpSolrServer("http://192.168.25.128:8080/solr/collection1");
        // 创建SolrQuery对象
        SolrQuery query = new SolrQuery();
        // 设置查询条件
        query.setQuery("*:*");
//        query.set("q", "*:*");
        // 查询并获取结果
        QueryResponse response = solrServer.query(query);
        SolrDocumentList results = response.getResults();
        // 遍历查询结果取出每一个域
        System.out.println("查询总记录数: " + results.getNumFound());
        for (SolrDocument result : results) {
            System.out.println("id: " + result.get("id"));
            System.out.println("item_sell_point: " + result.get("item_sell_point"));
            System.out.println("item_title: " + result.get("item_title"));
            System.out.println("item_image: " + result.get("item_image"));
            System.out.println("item_category_name: " + result.get("item_category_name"));

        }
    }

    @Test
    public void solrQueryFlex1() throws Exception {
        // 创建SolrServer对象
        SolrServer solrServer = new HttpSolrServer("http://192.168.25.128:8080/solr/collection1");
        // 创建查询对象并设置查询条件
        SolrQuery query = new SolrQuery();
        query.set(DEFAULT, "item_title");
        query.setQuery("手机");
        query.setHighlight(true);
        query.setHighlightSimplePre("<em>");
        query.setHighlightSimplePost("</em>");

        // 提交查询
        QueryResponse response = solrServer.query(query);

        // 获取hightlight结果, 如果highlight中没有结果则从normal中查找
        SolrDocumentList results = response.getResults();
        Map<String, Map<String, List<String>>> highlightingResults = response.getHighlighting();
        for (String key : highlightingResults.keySet()) {
            Map<String, List<String>> stringListMap = highlightingResults.get(key);
            System.out.println("key: " + key + ", value: " + stringListMap.get("item_title"));
        }
    }



}
