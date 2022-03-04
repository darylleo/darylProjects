package com.daryl.customtemplate.mapper;

import com.daryl.customtemplate.CustomProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wl
 * @create 2022-01-13
 */
@RegisterMapper
public interface CustomMapper<T>{
    @SelectProvider(
            type = CustomProvider.class,
            method = "dynamicSQL"
    )
    List<T> selectList();

    @SelectProvider(
            type = CustomProvider.class,
            method = "dynamicSQL"
    )
    T selectById(@Param("id") Long id);

    @SelectProvider(
            type = CustomProvider.class,
            method = "dynamicSQL"
    )
    <T,E> List<T> queryByPrimeIList(@Param("ids") List<E> idList);

    @UpdateProvider(
            type = CustomProvider.class,
            method = "dynamicSQL"
    )
    void deleteById(@Param("id") Long id);

    @SelectProvider(
            type = CustomProvider.class,
            method = "dynamicSQL"
    )
    List<T> selectByParams(@Param("params") List<Map<String, Object>> params);

    @SelectProvider(
            type = CustomProvider.class,
            method = "dynamicSQL"
    )
    List<Map<String, Object>> selectByLeftJoin(@Param("table") String table, @Param("name") String name, @Param("params") String params, @Param("columns") String columns, @Param("joins") List<Map<String, String>> joins);

    @SelectProvider(
            type = CustomProvider.class,
            method = "dynamicSQL"
    )
    List<Map<String, Object>> selectColumns(@Param("table") String table, @Param("name") String name, @Param("columns") String columns, @Param("params") String params);

    @SelectProvider(
            type = CustomProvider.class,
            method = "dynamicSQL"
    )
    List<Map<String, Object>> getGroupCount(@Param("table") String table, @Param("group") String group, @Param("params") List<Map<String, Object>> params);

    @SelectProvider(
            type = CustomProvider.class,
            method = "dynamicSQL"
    )
    String getTableAllCount(@Param("table") String table);

    @SelectProvider(
            type = CustomProvider.class,
            method = "dynamicSQL"
    )
    String getTableDeleteCount(@Param("table") String table);

    @SelectProvider(
            type = CustomProvider.class,
            method = "dynamicSQL"
    )
    Date getLastCreateTime(@Param("table") String table);

    @SelectProvider(
            type = CustomProvider.class,
            method = "dynamicSQL"
    )
    Date getLastModifiedTime(@Param("table") String table);

    @UpdateProvider(
            type = CustomProvider.class,
            method = "dynamicSQL"
    )
    void deleteByParams(@Param("table") String table, @Param("params") List<Map<String, Object>> params, @Param("time") Date date);

    @UpdateProvider(
            type = CustomProvider.class,
            method = "dynamicSQL"
    )
    void replace(T record);

    @UpdateProvider(
            type = CustomProvider.class,
            method = "dynamicSQL"
    )
    void updateByMap(@Param("table") String table, @Param("id") String id, @Param("params") Map<String, Object> params);

    @InsertProvider(
            type = CustomProvider.class,
            method = "dynamicSQL"
    )
    void insertByMap(@Param("table") String table, @Param("params") Map<String, Object> params);

    @InsertProvider(
            type = CustomProvider.class,
            method = "dynamicSQL"
    )
    void replaceByMap(@Param("table") String table, @Param("params") Map<String, Object> params);

    @InsertProvider(
            type = CustomProvider.class,
            method = "dynamicSQL"
    )
    void replaceList(@Param("table") String table, @Param("columns") List<String> columns, @Param("datas") List<List<Object>> datas);

    @SelectProvider(
            type = CustomProvider.class,
            method = "dynamicSQL"
    )
    List<Map<String, Object>> selectByMap(@Param("table") String table, @Param("params") List<Map<String, Object>> params);

    @UpdateProvider(
            type = CustomProvider.class,
            method = "dynamicSQL"
    )
    void createTable(@Param("table") String table, @Param("columns") List<String> columns);

    @UpdateProvider(
            type = CustomProvider.class,
            method = "dynamicSQL"
    )
    void dropTable(@Param("table") String table);

    @UpdateProvider(
            type = CustomProvider.class,
            method = "dynamicSQL"
    )
    void dropTableIfExists(@Param("table") String table);

    @InsertProvider(
            type = CustomProvider.class,
            method = "dynamicSQL"
    )
    void insertByList(@Param("params") List<? extends T> params);

    @InsertProvider(
            type = CustomProvider.class,
            method = "dynamicSQL"
    )
    void replaceByList(@Param("params") List<? extends T> params);
}
