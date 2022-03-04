package com.daryl.api;

import com.daryl.domain.dtos.QueryParamDTO;
import com.daryl.service.SecondHouseService;
import com.daryl.customtemplate.annotation.Error;
import com.daryl.domain.dtos.ValidOptions;
import com.daryl.domain.entity.SecondHouseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wl
 * @create 2022-02-08
 */
@RestController
@RequestMapping("/api/house")
public class HouseApi {
    @Autowired
    private SecondHouseService secondHouseService;

    /**
     * 注册用户添加房源信息
     *
     * @param secondHouseEntity 房源信息
     * @return null
     */
    @PreAuthorize("hasAuthority('ADD_HOUSE_INFO')")
    @Error
    @RequestMapping("/addHouseInfo")
    public Object addHouseInfo(@Validated({ValidOptions.Create.class}) @RequestBody SecondHouseEntity secondHouseEntity) {
        secondHouseService.addHouseInfo(secondHouseEntity);
        return null;
    }

    /**
     * 注册用户更改房源信息
     *
     * @param secondHouseEntity 房源信息
     * @return null
     */
    @Error
    @RequestMapping("/updateHouseInfo")
    public Object updateHouseInfo(@Validated({ValidOptions.Update.class}) @RequestBody SecondHouseEntity secondHouseEntity) {
        secondHouseService.updateHouseInfo(secondHouseEntity);
        return null;
    }

    /**
     * 注册用户删除房源信息
     *
     * @param secondHouseEntity 房源信息id
     * @return null
     */
    @Error
    @RequestMapping("/removeHouseInfo")
    public Object removeHouseInfo(@RequestBody SecondHouseEntity secondHouseEntity) {
        secondHouseService.removeHouseInfo(secondHouseEntity);
        return null;
    }

    /**
     * 注册用户查看自己发布的房源信息
     *
     * @param secondHouseEntity 房源信息
     * @return 用户自己发布的房源信息
     */
    @Error
    @RequestMapping("/queryUserHouseInfo")
    public Object queryUserHouseInfo(@RequestBody SecondHouseEntity secondHouseEntity) {
        return secondHouseService.queryUserHouseInfo(secondHouseEntity);
    }

    //游客 ↓

    /**
     * 游客查看某个二手房详情信息
     * @param secondHouseEntity 封装houseId
     * @return 详细房源信息
     */
    @Error
    @RequestMapping("/queryHouseInfoById")
    public Object queryHouseInfoById(@RequestBody SecondHouseEntity secondHouseEntity) {
        return secondHouseService.queryHouseInfoById(secondHouseEntity);
    }

    /**
     * 条件查询房源 信息
     * @param queryParamDTO 封装查询条件
     * @return 符合条件的房源信息
     */
    @Error
    @RequestMapping("/queryHouseInfoList")
    public Object queryHouseInfoList(@RequestBody QueryParamDTO queryParamDTO) {
        return secondHouseService.queryHouseInfoList(queryParamDTO);
    }
}
