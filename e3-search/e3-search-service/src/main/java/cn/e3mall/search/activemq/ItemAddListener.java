package cn.e3mall.search.activemq;

import cn.e3mall.search.mapper.ItemMapper;
import cn.e3mall.vo.resp.SearchItem;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * FileName: ItemAddListener
 * DESCRIPTION: activeMQ消息监听(商品添加)
 *
 * @author: SLY
 * @create: 2019/2/3
 */
public class ItemAddListener implements MessageListener {

    @Resource
    private ItemMapper itemMapper;

    @Autowired
    private SolrServer solrServer;

    @Override
    public void onMessage(Message message) {

        try {

            // message对象强转为TextMessage, 获取id
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            long itemId = Long.parseLong(text);

            // 根据id查询获取SearchItem对象
            // 考虑到消息发布的时候, mysql的数据库事务还没提交, 数据库还没数据。因此线程休眠一会。
            Thread.sleep(100);
            SearchItem item = itemMapper.getSearchItemByItemId(itemId);

            // 创建solr文档对象
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", item.getId());
            document.addField("item_title", item.getTitle());
            document.addField("item_sell_point", item.getSellPoint());
            document.addField("item_price", item.getPrice());
            document.addField("item_image", item.getImage());
            document.addField("item_category_name", item.getCategoryName());

            // 添加文档到solr索引库
            solrServer.add(document);

            // 提交
            solrServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
