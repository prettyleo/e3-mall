package cn.e3mall.service.impl;

import cn.e3mall.entity.model.TbItem;
import cn.e3mall.entity.model.TbItemDesc;
import cn.e3mall.jedis.JedisClient;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.service.ItemService;
import cn.e3mall.util.E3Result;
import cn.e3mall.util.IDUtils;
import cn.e3mall.vo.resp.PageResult;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.Date;
import java.util.List;

/**
 * FileName: ItemServiceImpl
 * DESCRIPTION: 商品管理实现类
 *
 * @author: Liyou Shen
 * @create: 2019/1/9
 */
@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    private TbItemMapper tbItemMapper;
    @Resource
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    @Qualifier("topicDestination")
    private Destination destination;
    @Autowired
    JedisClient jedisClient;
    @Value("${REDIS_ITEM_PRE}")
    private String REDIS_ITEM_PRE;
    @Value("${REDIS_ITEM_EXPIRE}")
    private Integer REDIS_ITEM_EXPIRE;


    @Override
    public TbItem getItemById(Long itemId) {
        TbItem tbItem = null;
        try {
            // 从缓存中查找数据,找不到再从数据库查找
            String jsonStr = jedisClient.get(REDIS_ITEM_PRE + ":" + itemId + ":BASE");
            tbItem = JSON.parseObject(jsonStr, TbItem.class);
            if (tbItem != null) {
                return tbItem;
            }
        } catch (Exception e) {
            log.error("从缓存查找商品详情失败，itemId: {}", itemId);
        }

        // 从数据库查找
        TbItem query = new TbItem();
        query.setId(itemId);
        tbItem = tbItemMapper.selectByPrimaryKey(query);

        // 数据库结果加入缓存
        try {
            jedisClient.set(REDIS_ITEM_PRE + ":" + itemId + ":BASE", JSON.toJSONString(tbItem));
            // 设置过期时间
            jedisClient.expire(REDIS_ITEM_PRE + ":" + itemId + ":BASE", REDIS_ITEM_EXPIRE);
        } catch (Exception e) {
            log.error("商品详情信息加入缓存失败, itemId: {}", itemId);
        }

        return tbItem;
    }

    @Override
    public PageResult<TbItem> getItemList(Integer page, Integer rows) {
        // 设置分页信息
        PageHelper.startPage(page, rows);

        // 执行查询
        List<TbItem> tbItemList = tbItemMapper.selectAll();

        // 取分页结果
        PageInfo<TbItem> pageInfo = new PageInfo<>(tbItemList);

        // 创建响应对象
        PageResult<TbItem> pageResult = new PageResult();
        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setRows(tbItemList);
        return pageResult;
    }

    @Override
    public E3Result addItem(TbItem item, String desc) {
        //生成商品id
        final long itemId = IDUtils.genItemId();
        //补全item的属性
        item.setId(itemId);
        //1-正常，2-下架，3-删除
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        //向商品表插入数据
        tbItemMapper.insert(item);
        //创建一个商品描述表对应的pojo对象。
        TbItemDesc itemDesc = new TbItemDesc();
        //补全属性
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        //向商品描述表插入数据
        tbItemDescMapper.insert(itemDesc);

        // 发布消息, 通知e3-search-service添加文档到solr索引库
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(String.valueOf(itemId));
                return textMessage;
            }
        });

        //返回成功
        return E3Result.ok();
    }
}

