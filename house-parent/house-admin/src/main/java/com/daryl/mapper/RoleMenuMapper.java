package com.daryl.mapper;


import com.daryl.domain.entity.user.MenuEntity;
import com.daryl.domain.entity.user.RoleMenuEntity;
import com.daryl.customtemplate.mapper.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 角色权限关联表
 *
 * @author wl
 * @create 2022-01-29
 */
public interface RoleMenuMapper extends MyMapper<RoleMenuEntity> {
    List<MenuEntity> queryMenuByUserId(@Param("userId") Long userId);

    /**
     * 逻辑删除角色权限关联表（根据角色id）
     *
     * @param roleId   角色id
     * @param username 用户名
     */
    void removeRoleMenuByRoleId(@Param("roleId") Long roleId, @Param("username") String username);

    /**
     * 逻辑删除角色权限关联表（根据用户id）
     *
     * @param menuId   角色id
     * @param username 用户名
     */
    void removeRoleMenuByMenuId(@Param("menuId") Long menuId, @Param("username") String username);

    @Select("SELECT id,create_by,create_time,modify_by,modify_time,role_id,menu_id FROM t_sys_role_menu WHERE role_id = #{roleId} AND del_tag = 0")
    List<RoleMenuEntity> queryRoleMenuByRoleId(@Param("roleId") Long roleId);
}
