package com.daryl.domain.entity.user;

import com.daryl.domain.entity.MyEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户角色关联表
 * @author wl
 * @create 2022-01-27
 */
@Table(name = "T_SYS_USER_ROLE")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleEntity extends MyEntity {

    @Column(name = "ROLE_ID")
    private Long roleId;

    @Column(name = "USER_ID")
    private Long userId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
