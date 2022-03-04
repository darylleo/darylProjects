package com.daryl.domain.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 房源详情dto对象
 *
 * @author wl
 * @create 2022-02-12
 */
@Getter
@Setter
public class HouseDetailInfoDTO {
    //床
    private Integer hasBed;

    //空调
    private Integer hasAirCondition;

    //衣柜
    private Integer hasWardrobe;

    //电视
    private Integer hasTelevision;

    //暖气
    private Integer hasHeating;

    //阳台
    private Integer hasBalcony;

    //卫生间
    private Integer hasTOILET;

    //智能门锁
    private Integer hasIntelligentDoorLock;

    //冰箱
    private Integer hasRefrigerator;

    //洗衣机
    private Integer hasWashingMachine;

    //沙发
    private Integer hasSofa;

    //油烟机
    private Integer hasLampblackMachine;

    //可做饭
    private Integer cook;

    //宽带
    private Integer hasBroadband;

    //热水器
    private Integer hasHeater;

    //煤气灶
    private Integer hasGasCooker;

    //房屋亮点
    private String houseHighlights;

    //出租要求
    private String rentalRequirements;

    //房源描述
    private String houseDescription;
}
