package com.daryl.service;


import com.daryl.domain.dto.RoleMenuDTO;
import com.daryl.domain.dto.UserRoleDTO;
import com.daryl.domain.entity.user.MenuEntity;
import com.daryl.domain.entity.user.RoleEntity;
import com.daryl.domain.entity.user.UserEntity;

import com.daryl.domain.dtos.QueryParamDTO;

import com.github.pagehelper.PageInfo;

/**
 * @author wl
 * @create 2022-01-13
 */
public interface UserService {

    /**
     * 添加用户
     *
     * @param userEntity 用户信息
     */
    void createUser(UserEntity userEntity);

    /**
     * 更改用户信息
     *
     * @param userEntity 用户信息参数
     */
    void updateUser(UserEntity userEntity);

    /**
     * 删除用户信息
     *
     * @param userEntity 用户id参数
     */
    void deleteUser(UserEntity userEntity);

    /**
     * 用户登录
     *
     * @param userEntity 用户名密码参数
     * @return token及用户信息
     */
    String login(UserEntity userEntity);

    /**
     * 用户退出登录
     */
    void logout();

    /**
     * 管理员查看所有用户信息
     *
     * @param queryParamDTO 封装查询条件
     * @return 所有用户信息
     */
    PageInfo<UserEntity> queryAllUsers(QueryParamDTO queryParamDTO);

    /**
     * 添加角色
     *
     * @param roleEntity 角色信息
     */
    void createRole(RoleEntity roleEntity);

    /**
     * 更改角色信息
     *
     * @param roleEntity 角色信息
     */
    void updateRole(RoleEntity roleEntity);

    /**
     * 删除角色
     *
     * @param roleEntity 角色id
     */
    void deleteRole(RoleEntity roleEntity);

    /**
     * 条件查询所有角色信息
     *
     * @param queryParamDTO 封装查询条件
     * @return 角色信息
     */
    PageInfo<RoleEntity> queryAllRoles(QueryParamDTO queryParamDTO);

    /**
     * 添加、修改权限
     *
     * @param menuEntity 权限信息
     */
    void createMenu(MenuEntity menuEntity);

    /**
     * 更改权限信息
     *
     * @param menuEntity 权限信息
     */
    void updateMenu(MenuEntity menuEntity);

    /**
     * 删除权限信息
     *
     * @param menuEntity 权限信息
     */
    void deleteMenu(MenuEntity menuEntity);

    /**
     * 查询所有权限信息
     *
     * @param queryParamDTO 封装查询条件
     * @return 权限信息
     */
    PageInfo<MenuEntity> queryAllMenus(QueryParamDTO queryParamDTO);

    /**
     * 用户绑定角色
     *
     * @param userRoleDTO 封装用户id和角色id
     */
    void bindRolesToUser(UserRoleDTO userRoleDTO);

    /**
     * 角色绑定权限
     *
     * @param roleMenuDto 封装角色id、权限id
     */
    void bindMenusToRole(RoleMenuDTO roleMenuDto);

    /**
     * 创建注册用户信息
     *
     * @param userEntity 封装注册用户信息
     */
    void createRegisterUser(UserEntity userEntity);
}
