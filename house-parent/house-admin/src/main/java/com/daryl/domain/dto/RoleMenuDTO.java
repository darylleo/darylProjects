package com.daryl.domain.dto;


import com.daryl.domain.dtos.ValidOptions;
import com.daryl.domain.entity.user.MenuEntity;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 角色权限关联信息
 * @author wl
 * @create 2022-02-11
 */
@Data
public class RoleMenuDTO {
    /**
     * 角色id
     */
    @NotNull(message = "角色id不能为空",groups = {ValidOptions.Create.class})
    private Long roleId;

    /**
     * 权限集合
     */
    @NotEmpty(message = "权限id不能为空" ,groups = {ValidOptions.Create.class})
    private List<MenuEntity> menuList;
}
