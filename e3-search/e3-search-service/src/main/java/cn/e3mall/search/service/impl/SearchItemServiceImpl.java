package cn.e3mall.search.service.impl;

import cn.e3mall.search.mapper.ItemMapper;
import cn.e3mall.service.SearchItemService;
import cn.e3mall.util.E3Result;
import cn.e3mall.vo.resp.SearchItem;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * FileName: SearchItemServiceImpl
 * DESCRIPTION: 索引库维护service
 *
 * @author: SLY
 * @create: 2019/1/28
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {

    @Resource
    private ItemMapper itemMapper;
    @Autowired
    private SolrServer solrServer;

    /**
     * @Description: 将商品信息导入solr索引库
     * @param:
     * @Return: cn.e3mall.util.E3Result
     * @Author: SLY
     * @Date:   2019/1/28 21:53
     */
    @Override
    public E3Result importAllItems() {
        try {
            // 查询商品列表
            List<SearchItem> itemList = itemMapper.getItemList();
            // 遍历商品列表
            for (SearchItem item : itemList) {
                // 创建文档对象
                SolrInputDocument document = new SolrInputDocument();
                // 向文档对象添加业务域
                document.addField("id", item.getId());
                document.addField("item_title", item.getTitle());
                document.addField("item_sell_point", item.getSellPoint());
                document.addField("item_price", item.getPrice());
                document.addField("item_image", item.getImage());
                document.addField("item_category_name", item.getCategoryName());
                // 把文档写入索引库
                solrServer.add(document);
            }
            // 提交
            solrServer.commit();
            return E3Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return E3Result.build(500, "导入数据到索引库异常");
        }
    }
}
