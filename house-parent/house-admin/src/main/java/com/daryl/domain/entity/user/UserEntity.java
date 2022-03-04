package com.daryl.domain.entity.user;



import com.daryl.domain.dtos.ValidOptions;
import com.daryl.domain.entity.MyEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 用户
 *
 * @author wl
 * @create 2022-01-12
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SYS_USER")
public class UserEntity extends MyEntity {

    @NotBlank(message = "用户名不能为空", groups = {ValidOptions.Create.class, ValidOptions.Update.class})
    @Column(name = "USERNAME")
    private String username;

    @Column(name = "TRUE_NAME")
    private String trueName;

    //@NotBlank(message = "密码不能为空", groups = {ValidOptions.Create.class, ValidOptions.Update.class})
    @Column(name = "PASSWORD")
    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "EXPIRED")
    private Date expired;

    @Column(name = "LOCKED")
    private Integer locked = 0;

    @Column(name = "ENABLED")
    private Integer enabled = 1;

    @Column(name = "IS_EXPIRED")
    private Integer isExpired = 0;

    //头像
    @Column(name = "IMG")
    private String img;

    /**
     * 性别（0男1女）
     */
    @Column(name = "GENDER")
    private Integer gender;

    //地址
    @Column(name = "ADDRESS")
    private String address;

    //个性签名
    @Column(name = "PERSONAL_SIGNATURE")
    private String personalSignature;

    /**
     * 我的收藏
     */
    @Column(name = "COLLECTIONS")
    private String collections;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getIsExpired() {
        return isExpired;
    }

    public void setIsExpired(Integer isExpired) {
        this.isExpired = isExpired;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPersonalSignature() {
        return personalSignature;
    }

    public void setPersonalSignature(String personalSignature) {
        this.personalSignature = personalSignature;
    }

    public String getCollections() {
        return collections;
    }

    public void setCollections(String collections) {
        this.collections = collections;
    }
}
