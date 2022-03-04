package com.daryl.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wl
 * @create 2022-02-08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto implements Serializable {

    private Long id;

    private Date createTime;

    private Date modifyTime;

    private String createBy;

    private String modifyBy;

    /**
     * 是否逻辑删除（0否1是）
     */
    private Integer delTag = 0;

    private String username;

    private String trueName;

    private String password;

    private Date expired;

    private Integer locked = 0;

    private Integer enabled = 1;

    private Integer isExpired = 0;

    //头像
    private String img;

    /**
     * 性别（0男1女）
     */
    private Integer gender;

    //地址
    private String address;

    //个性签名
    private String personalSignature;

    /**
     * 我的收藏
     */
    private String collections;
}
