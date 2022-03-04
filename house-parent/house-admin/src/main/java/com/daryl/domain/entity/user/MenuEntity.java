package com.daryl.domain.entity.user;

import com.daryl.domain.dtos.ValidOptions;
import com.daryl.domain.entity.MyEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * 权限表
 * @author wl
 * @create 2022-01-27
 */
@Table(name = "T_SYS_MENU")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MenuEntity extends MyEntity {

    /**
     * 权限名称
     */
    @NotBlank(message = "权限名称不能为空",groups = {ValidOptions.Create.class,ValidOptions.Update.class})
    @Column(name = "MENU_NAME")
    private String menuName;

    /**
     * 权限编码
     */
    @NotBlank(message = "权限编码不能为空",groups = {ValidOptions.Create.class,ValidOptions.Update.class})
    @Column(name = "MENU_CODE")
    private String menuCode;

    /**
     * 权限描述
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * 状态（0启用，1停用）
     */
    @Column(name = "STATUS")
    private Integer status;


    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
