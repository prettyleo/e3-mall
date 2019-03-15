package cn.e3mall.search.mapper;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;

/**
 * FileName: SolrCloudServer
 * DESCRIPTION:
 *
 * @author: SLY
 * @create: 2019/2/2
 */
public class SolrCloudServer {

    /**
     * @Description: solr-cloud 向索引库添加文档
     * @param:
     * @Return: void
     * @Author: SLY
     * @Date:   2019/2/2 23:14
     */
    @Test
    public void testAddField() throws IOException, SolrServerException {
        // 创建SolrCloud对象, 参数是zookeeper集群IP, 逗号分隔
        SolrServer solrServer = new CloudSolrServer("192.168.25.128:2184,192.168.25.128:2182,192.168.25.128:2183");

        // 设置默认collection
        ((CloudSolrServer) solrServer).setDefaultCollection("collection2");

        // 创建文档对象, 添加业务域
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", "solrCloudId");
        document.addField("item_title", "happy new year");
        document.addField("item_price", 1000);
        // 把文档添加到索引库, 保存
        solrServer.add(document);

        // 提交
        solrServer.commit();
    }

    @Test
    public void query() throws SolrServerException {
        // 创建Solrcloud对象
        SolrServer solrServer = new CloudSolrServer("192.168.25.128:2184,192.168.25.128:2182,192.168.25.128:2183");

        // 设置默认的collection
        ((CloudSolrServer) solrServer).setDefaultCollection("collection2");

        // 创建查询对象, 设置查询条件
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");

        // 查询
        QueryResponse response = solrServer.query(query);
        SolrDocumentList results = response.getResults();

        for (SolrDocument result : results) {
            Object item_title = result.get("item_title");
            System.out.println(item_title);
        }
    }

}
