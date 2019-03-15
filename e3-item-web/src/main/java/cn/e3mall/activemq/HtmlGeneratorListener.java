package cn.e3mall.activemq;

import cn.e3mall.entity.model.TbItem;
import cn.e3mall.entity.model.TbItemDesc;
import cn.e3mall.service.ItemDescService;
import cn.e3mall.service.ItemService;
import cn.e3mall.util.BeanUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import pojo.Item;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * FileName: HtmlGeneratorListener
 * DESCRIPTION: 监听商品添加, 添加对应的静态页面
 *
 * @author: SLY
 * @create: 2019/2/6
 */
public class HtmlGeneratorListener implements MessageListener {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemDescService itemDescService;
    @Autowired
    private FreeMarkerConfig freeMarkerConfig;
    @Value("${HTML_GEN_PATH}")
    private String HTML_GEN_PATH;

    @Override
    public void onMessage(Message message) {
        try {


            // 创建一个模板, 参考jsp
            // 从消息中取出商品id
            TextMessage textMessage = (TextMessage) message;
            String text = ((TextMessage) message).getText();
            Long itemId = Long.parseLong(text);

            // 等待商品数据入库
            Thread.sleep(1000);

            // 根据商品id查询商品信息(商品基本信息和商品描述)
            TbItem tbItem = itemService.getItemById(itemId);
            Item item = BeanUtils.a2b(tbItem, Item.class);
            item.setImages(tbItem.getImage());
            TbItemDesc itemDesc = itemDescService.getItemDescByItemId(itemId);

            // 创建一个数据集, 把商品数据封装
            Map map = new HashMap(16);
            map.put("item", item);
            map.put("itemDesc", itemDesc);

            // 加载模板对象
            Configuration configuration = freeMarkerConfig.getConfiguration();
            Template template = configuration.getTemplate("item.ftl");

            // 创建一个输出流, 知道你个输出的目录及文件名
            Writer writer = new FileWriter(HTML_GEN_PATH + itemId + ".html");

            // 生成静态页面
            template.process(map, writer);

            // 关闭流
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
