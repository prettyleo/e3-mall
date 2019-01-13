package cn.e3mall.tk;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * mybatis通用mapper
 * 注意: 此接口不能被扫描到
 * @param <T>
 */
public interface ExpandMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
