package com.daryl.domain.entity.user;

import com.daryl.domain.entity.MyEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 角色权限关联表
 * @author wl
 * @create 2022-01-27
 */
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SYS_ROLE_MENU")
@Entity
public class RoleMenuEntity extends MyEntity {

    @Column(name = "ROLE_ID")
    private Long roleId;

    @Column(name = "MENU_ID")
    private Long menuId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
