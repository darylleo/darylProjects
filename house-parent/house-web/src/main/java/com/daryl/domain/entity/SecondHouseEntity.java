package com.daryl.domain.entity;

import com.daryl.domain.dtos.ValidOptions;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * 二手房信息
 *
 * @author wl
 * @create 2022-02-08
 */
@Entity
@Table(name = "T_SECOND_HOUSE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SecondHouseEntity extends MyEntity {

    //标题
    @NotBlank(message = "标题不能为空",groups = {ValidOptions.Create.class,ValidOptions.Update.class})
    @Column(name = "TITLE")
    private String title;

    //价格
    @NotBlank(message = "价格不能为空",groups = {ValidOptions.Create.class,ValidOptions.Update.class})
    @Column(name = "PRICE")
    private String price;

    //付费标准（押一付一\押一付三）
    @NotBlank(message = "付费标准不能为空",groups = {ValidOptions.Create.class,ValidOptions.Update.class})
    @Column(name = "PAYMENT_STANDARD")
    private String paymentStandard;

    //租赁方式
    @NotBlank(message = "租赁方式不能为空",groups = {ValidOptions.Create.class,ValidOptions.Update.class})
    @Column(name = "LEASE_MODE")
    private String leaseMode;

    //房屋类型
    @NotBlank(message = "房屋类型不能为空",groups = {ValidOptions.Create.class,ValidOptions.Update.class})
    @Column(name = "HOUSE_TYPE")
    private String houseType;

    //朝向楼层
    @NotBlank(message = "朝向、楼层不能为空",groups = {ValidOptions.Create.class,ValidOptions.Update.class})
    @Column(name = "TOWARD_FLOOR")
    private String towardsFloor;

    //所在小区
    @NotBlank(message = "所在小区不能为空",groups = {ValidOptions.Create.class,ValidOptions.Update.class})
    @Column(name = "COMMUNITY")
    private String community;

    //所属区域
    @NotBlank(message = "所属区域不能为空",groups = {ValidOptions.Create.class,ValidOptions.Update.class})
    @Column(name = "REGION")
    private String region;

    //详细地址
    @NotBlank(message = "详细地址不能为空",groups = ValidOptions.Create.class)
    @Column(name = "DETAILED_ADDRESS")
    private String detailedAddress;

    //房源详情
    @NotBlank(message = "房源详情不能为空",groups = {ValidOptions.Create.class,ValidOptions.Update.class})
    @Column(name = "HOUSE_DETAIL_INFO")
    private String houseDetailInfo;

    //图片
    @Column(name = "IMG")
    private String img;

    //浏览量
    @Column(name = "VIEW_COUNT")
    private String viewCount;

    //收藏数量
    @Column(name = "COLLECTION_NUM")
    private String collectionNum;

    //发布人
    @NotBlank(message = "发布人不能为空",groups = ValidOptions.Create.class)
    @Column(name = "USER_ID")
    private Long userId;

    /**
     * 请求地址
     */
    //@NotBlank(message = "请求地址不能为空",groups = {ValidOptions.Create.class,ValidOptions.Update.class})
    @Column(name = "REQUEST_RUL")
    private String requestUrl;

    //发布角色
    @Column(name = "PUBLISH_ROLE")
    private String publishRole;

}
