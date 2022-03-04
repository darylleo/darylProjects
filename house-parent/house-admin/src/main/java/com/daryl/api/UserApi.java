package com.daryl.api;


import com.daryl.domain.dto.RoleMenuDTO;
import com.daryl.domain.dto.UserRoleDTO;
import com.daryl.domain.entity.user.MenuEntity;
import com.daryl.domain.entity.user.RoleEntity;
import com.daryl.domain.entity.user.UserEntity;
import com.daryl.service.UserService;
import com.daryl.domain.dtos.QueryParamDTO;
import com.daryl.customtemplate.annotation.Error;

import com.daryl.domain.dtos.ValidOptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wl
 * @create 2022-01-13
 */
@RestController
@RequestMapping("/api/user")
public class UserApi {
    @Autowired
    private UserService userService;

    @Error
    @RequestMapping("/say")
    public Object sayHello() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("zhangSan");
        userEntity.setPassword("112");
        return userEntity;
    }

    /**
     * 用户登录
     *
     * @param userEntity 登录校验参数
     * @return token及用户信息
     */
    @PostMapping("/login")
    @Error
    public Object login(@Validated({ValidOptions.Create.class}) @RequestBody UserEntity userEntity) {
        return userService.login(userEntity);
    }

    /**
     * 用户退出
     *
     * @return null
     */
    @PostMapping("/logout")
    @Error
    public Object logout() {
        userService.logout();
        return null;
    }

    /**
     * 创建用户
     *
     * @param userEntity 用户信息参数
     * @return null
     */
    @PreAuthorize("hasAuthority('CREATE_USER')")
    @PostMapping("/createUser")
    @Error
    public Object createUser(@Validated({ValidOptions.Create.class}) @RequestBody UserEntity userEntity) {
        userService.createUser(userEntity);
        return null;
    }

    /**
     * 更改用户信息
     *
     * @param userEntity 用户信息参数
     * @return null
     */
    @PreAuthorize("hasAuthority('UPDATE_USER')")
    @PostMapping("/updateUser")
    @Error
    public Object updateUser(@Validated({ValidOptions.Update.class}) @RequestBody UserEntity userEntity) {
        userService.updateUser(userEntity);
        return null;
    }

    /**
     * 删除用户信息
     *
     * @param userEntity 封装参数
     * @return null
     */
    @PreAuthorize("hasAuthority('DELETE_USER')")
    @PostMapping("/deleteUser")
    @Error
    public Object deleteUser(@RequestBody UserEntity userEntity) {
        userService.deleteUser(userEntity);
        return null;
    }

    /**
     * 查询所有用户信息
     *
     * @param queryParamDTO 封装查询条件参数
     * @return 所有用户信息（包含条件查询）
     */
    @PreAuthorize("hasAuthority('QUERY_ALL_USERS')")
    @PostMapping("/queryAllUsers")
    @Error
    public Object queryAllUsers(@RequestBody QueryParamDTO queryParamDTO) {
        return userService.queryAllUsers(queryParamDTO);
    }

    /**
     * 添加角色信息
     *
     * @param roleEntity 角色信息封装参数
     * @return null
     */
    @PreAuthorize("hasAuthority('CREATE_ROLE')")
    @PostMapping("/createRole")
    @Error
    public Object createRole(@Validated(ValidOptions.Create.class) @RequestBody RoleEntity roleEntity) {
        userService.createRole(roleEntity);
        return null;
    }

    /**
     * 更新角色信息
     *
     * @param roleEntity 角色信息封装
     * @return null
     */
    @PreAuthorize("hasAuthority('UPDATE_ROLE')")
    @PostMapping("/updateRole")
    @Error
    public Object updateRole(@Validated(ValidOptions.Update.class) @RequestBody RoleEntity roleEntity) {
        userService.updateRole(roleEntity);
        return null;
    }

    /**
     * 删除角色信息
     *
     * @param roleEntity 封装角色id参数
     * @return null
     */
    @PreAuthorize("hasAuthority('DELETE_ROLE')")
    @PostMapping("/deleteRole")
    @Error
    public Object deleteRole(@RequestBody RoleEntity roleEntity) {
        userService.deleteRole(roleEntity);
        return null;
    }

    /**
     * 查询所有角色信息（条件查询）
     *
     * @param queryParamDTO 封装查询参数
     * @return 角色信息
     */
    @PreAuthorize("hasAuthority('QUERY_ALL_ROLES')")
    @PostMapping("/queryAllRoles")
    @Error
    public Object queryAllRoles(@RequestBody QueryParamDTO queryParamDTO) {
        return userService.queryAllRoles(queryParamDTO);
    }

    /**
     * 添加权限信息
     *
     * @param menuEntity 权限信息
     * @return null
     */
    @PreAuthorize("hasAuthority('CREATE_MENU')")
    @PostMapping("/createMenu")
    @Error
    public Object createMenu(@Validated(ValidOptions.Create.class) @RequestBody MenuEntity menuEntity) {
        userService.createMenu(menuEntity);
        return null;
    }

    /**
     * 更改权限信息
     *
     * @param menuEntity 权限信息参数
     * @return null
     */
    @PreAuthorize("hasAuthority('UPDATE_MENU')")
    @PostMapping("/updateMenu")
    @Error
    public Object updateMenu(@Validated(ValidOptions.Update.class) @RequestBody MenuEntity menuEntity) {
        userService.updateMenu(menuEntity);
        return null;
    }

    /**
     * 删除权限信息
     *
     * @param menuEntity 封装权限id
     * @return null
     */
    @PreAuthorize("hasAuthority('DELETE_MENU')")
    @PostMapping("/deleteMenu")
    @Error
    public Object deleteMenu(@RequestBody MenuEntity menuEntity) {
        userService.deleteMenu(menuEntity);
        return null;
    }

    /**
     * 条件查询所有权限信息
     *
     * @param queryParamDTO 封装条件查询参数
     * @return 权限信息
     */
    @PreAuthorize("hasAuthority('QUERY_ALL_MENUS')")
    @PostMapping("/queryAllMenus")
    @Error
    public Object queryAllMenus(@RequestBody QueryParamDTO queryParamDTO) {
        return userService.queryAllMenus(queryParamDTO);
    }

    /**
     * 给用户绑定角色
     *
     * @param userRoleDTO 用户id和关联角色信息
     * @return null
     */
    @PreAuthorize("hasAuthority('BIND_ROLES_TO_USER')")
    @PostMapping("/bindRolesToUser")
    @Error
    public Object bindRolesToUser(@Validated({ValidOptions.Create.class}) @RequestBody UserRoleDTO userRoleDTO) {
        userService.bindRolesToUser(userRoleDTO);
        return null;
    }

    /**
     * 给用户绑定角色
     *
     * @param roleMenuDto 角色id和关联权限信息
     * @return null
     */
    @PreAuthorize("hasAuthority('BIND_MENUS_TO_ROLE')")
    @PostMapping("/bindMenusToRole")
    @Error
    public Object bindMenusToRole(@Validated({ValidOptions.Create.class}) @RequestBody RoleMenuDTO roleMenuDto) {
        userService.bindMenusToRole(roleMenuDto);
        return null;
    }

    /**
     * 创建注册用户
     *
     * @param userEntity 用户信息
     * @return null
     */
    @PostMapping("/createRegisterUser")
    @Error
    public Object createRegisterUser(@Validated({ValidOptions.Create.class}) @RequestBody UserEntity userEntity) {
        userService.createRegisterUser(userEntity);
        return null;
    }
}
