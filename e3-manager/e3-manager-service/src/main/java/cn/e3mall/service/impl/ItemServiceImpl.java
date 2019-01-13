package cn.e3mall.service.impl;

import cn.e3mall.entity.model.TbItem;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.service.ItemService;
import cn.e3mall.vo.resp.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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


    public ItemServiceImpl() {
        System.out.println(Thread.currentThread().getName() + "创建service实例");
    }

    @Override
    public TbItem getItemById(Long itemId) {
        TbItem query = new TbItem();
        query.setId(itemId);
        return tbItemMapper.selectByPrimaryKey(query);
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
}

