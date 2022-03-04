package com.daryl.service;

import com.daryl.domain.dtos.QueryParamDTO;
import com.daryl.domain.entity.SecondHouseEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 二手房接口
 * @author wl
 * @create 2022-02-08
 */
public interface SecondHouseService {
    /**
     * 注册用户发布房源信息
     * @param secondHouseEntity 房源信息
     */
    void addHouseInfo(SecondHouseEntity secondHouseEntity);

    /**
     * 注册用户修改房源信息
     * @param secondHouseEntity 房源信息
     */
    void updateHouseInfo(SecondHouseEntity secondHouseEntity);

    /**
     * 注册用户删除房源信息
     * @param secondHouseEntity 房源信息
     */
    void removeHouseInfo(SecondHouseEntity secondHouseEntity);

    /**
     * 注册用户查看自己发布的房源信息
     * @param secondHouseEntity 房源信息
     * @return result
     */
    List<SecondHouseEntity> queryUserHouseInfo(SecondHouseEntity secondHouseEntity);

    /**
     * 游客查看某个房源的详细信息
     * @param secondHouseEntity 封装参数
     * @return 房源详细信息
     */
    SecondHouseEntity queryHouseInfoById(SecondHouseEntity secondHouseEntity);

    /**
     * 条件查询房源信息
     * @param queryParamDTO 封装查询条件
     * @return 符合条件的房源信息
     */
    PageInfo<SecondHouseEntity> queryHouseInfoList(QueryParamDTO queryParamDTO);
}
