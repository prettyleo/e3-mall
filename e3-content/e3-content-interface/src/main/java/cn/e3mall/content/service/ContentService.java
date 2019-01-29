package cn.e3mall.content.service;

import cn.e3mall.entity.model.TbContent;

import java.util.List;

/**
 * FileName: ContentService
 * DESCRIPTION: 内容服务
 *
 * @author: SLY
 * @create: 2019/1/23
 */
public interface ContentService {

    /**
     * @Description: 根据categoryId查询内容
     * @param:  cid category_id字段
     * @Return: java.util.List<cn.e3mall.entity.model.TbContent>
     * @Author: SLY
     * @Date:   2019/1/23 23:03
     */
    List<TbContent> getContentListByCid(long cid);
}
