package cn.e3mall.service.impl;

import cn.e3mall.entity.model.TbItem;
import cn.e3mall.entity.model.TbItemDesc;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.service.ItemService;
import cn.e3mall.util.E3Result;
import cn.e3mall.util.IDUtils;
import cn.e3mall.vo.resp.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Override
    public E3Result addItem(TbItem item, String desc) {
        //生成商品id
        long itemId = IDUtils.genItemId();
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
        //返回成功
        return E3Result.ok();
    }
}

