package cn.e3mall.content.service.impl;

import cn.e3mall.content.service.ContentService;
import cn.e3mall.entity.model.TbContent;
import cn.e3mall.jedis.JedisClient;
import cn.e3mall.mapper.TbContentMapper;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * FileName: ContentServiceImpl
 * DESCRIPTION:
 *
 * @author: SLY
 * @create: 2019/1/23
 */
@Slf4j
@Service
public class ContentServiceImpl implements ContentService {

    @Resource
    private TbContentMapper tbContentMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${CONTENT_LIST}")
    private String CONTENT_LIST;

    /**
     * @Description: 根据categoryId查询内容
     * @param:  cid category_id字段
     * @Return: List<TbContent>
     * @Author: SLY
     * @Date:   2019/1/23 23:03
     */
    @Override
    public List<TbContent> getContentListByCid(long cid) {
        List<TbContent> contentList = null;
        /**
         * 从缓存中查找, 找不到才查数据库, 注意考虑查询缓存的时候可能会抛异常,
         * 抛异常不能影响下面的代码执行, 因此需要try catch
         */
        try {
            String objStr = jedisClient.hget(CONTENT_LIST, String.valueOf(cid));
            return JSONArray.parseArray(objStr, TbContent.class);
        } catch (Exception e) {
            log.error("从缓存查询内容列表出错: {}", e);
        }

        TbContent query = new TbContent();
        query.setCategoryId(cid);
        contentList = tbContentMapper.select(query);

        try {
            // 查询完数据库之后, 将结果缓存到数据库中
            String contentListStr = JSONObject.toJSONString(contentList);
            jedisClient.hset(CONTENT_LIST, String.valueOf(cid), contentListStr);
        } catch (Exception e) {
            log.error("内容列表加入缓存出错: {}", e);
        }

        return contentList;
    }
}
