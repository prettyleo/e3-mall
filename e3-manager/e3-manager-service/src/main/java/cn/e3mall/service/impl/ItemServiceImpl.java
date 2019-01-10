package cn.e3mall.service.impl;

import cn.e3mall.entity.model.TbItem;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.service.ItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * FileName: ItemServiceImpl
 * DESCRIPTION: 商品管理实现类
 *
 * @author: Liyou Shen
 * @create: 2019/1/9
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    private TbItemMapper tbItemMapper;


    public ItemServiceImpl() {
        System.out.println(Thread.currentThread().getName() + "创建service实例");
    }

    @Override
    public TbItem getItemById(Long itemId) {
        System.out.println("请求开始");
        System.out.println(tbItemMapper.toString());

        try {
            tbItemMapper.selectByPrimaryKey(itemId);
        } catch (Exception e) {
            System.out.println("Druid异常");
            e.printStackTrace();
        }

        return  null;
    }
}

