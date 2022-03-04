package com.daryl.domain.dto;


import com.daryl.domain.dtos.ValidOptions;
import com.daryl.domain.entity.user.RoleEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author wl
 * @create 2022-02-11
 */
@Data
public class UserRoleDTO {
    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空",groups = {ValidOptions.Create.class})
    private Long userId;

    /**
     * 角色信息集合
     */
    @NotEmpty
    private List<RoleEntity> roleList;
}
