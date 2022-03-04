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
 * 角色
 * @author wl
 * @create 2022-01-27
 */
@Table(name = "T_SYS_ROLE")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity extends MyEntity {

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空",groups = {ValidOptions.Create.class,ValidOptions.Update.class})
    @Column(name = "ROLE_NAME")
    private String roleName;

    /**
     * 角色编码
     */
    @NotBlank(message = "角色编码不能为空",groups = {ValidOptions.Create.class,ValidOptions.Update.class})
    @Column(name = "ROLE_CODE")
    private String roleCode;

    /**
     * 角色状态（0启用，1停用）
     */
    @Column(name = "STATUS")
    private Integer status;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
