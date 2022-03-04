package com.daryl.mapper;


import com.daryl.domain.entity.user.RoleEntity;
import com.daryl.domain.entity.user.UserRoleEntity;
import com.daryl.customtemplate.mapper.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色关联
 *
 * @author wl
 * @create 2022-02-10
 */
public interface UserRoleMapper extends MyMapper<UserRoleEntity> {
    /**
     * 逻辑删除关联表信息
     *
     * @param userId   用户id
     * @param username 用户名
     */
    void removeUserRoleByUserId(@Param("userId") Long userId, @Param("username") String username);

    /**
     * 逻辑删除关联表信息
     *
     * @param roleId   用户id
     * @param username 用户名
     */
    void removeUserRoleByRoleId(@Param("roleId") Long roleId, @Param("username") String username);

    /**
     * 根据用户id查询用户角色信息
     *
     * @param userId 用户id
     * @return 角色信息
     */
    List<RoleEntity> queryListRoleByUserId(@Param("userId") Long userId);
}
