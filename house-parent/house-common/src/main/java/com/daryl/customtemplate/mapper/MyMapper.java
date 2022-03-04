package com.daryl.customtemplate.mapper;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author wl
 * @create 2022-01-13
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T>, CustomMapper<T> {
}
