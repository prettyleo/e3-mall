package cn.e3mall.mapper;

import cn.e3mall.entity.model.TbItem;
import org.apache.ibatis.annotations.Param;

public interface TbItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbItem record);

    int insertSelective(TbItem record);

    TbItem selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(TbItem record);

    int updateByPrimaryKey(TbItem record);
}