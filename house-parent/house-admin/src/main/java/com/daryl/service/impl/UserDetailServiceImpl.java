package com.daryl.service.impl;


import com.daryl.domain.entity.user.MenuEntity;
import com.daryl.domain.entity.user.RoleEntity;
import com.daryl.domain.entity.user.UserEntity;
import com.daryl.mapper.RoleMenuMapper;
import com.daryl.mapper.UserMapper;
import com.daryl.domain.dtos.LoginUser;
import com.daryl.customtemplate.MyException;

import com.daryl.domain.dtos.UserInfoDto;

import com.daryl.mapper.UserRoleMapper;
import lombok.Data;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wl
 * @create 2022-01-24
 */
@Data
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        Example example = new Example(UserEntity.class);
        example.createCriteria().andEqualTo("username", username);
        UserEntity userEntity = userMapper.selectOneByExample(example);
        if (Objects.isNull(userEntity)){
            throw MyException.error("用户名或密码错误");
        }
        UserInfoDto userInfoDto = new UserInfoDto();
        BeanUtils.copyProperties(userEntity,userInfoDto);
        //todo 查询用户权限信息、角色信息
        List<String> menuList = roleMenuMapper.queryMenuByUserId(userEntity.getId())
                .stream()
                .map(MenuEntity::getMenuCode)
                .collect(Collectors.toList());
        List<String> roleList = userRoleMapper.queryListRoleByUserId(userEntity.getId())
                .stream()
                .map(RoleEntity::getRoleCode)
                .collect(Collectors.toList());
        return new LoginUser(userInfoDto,menuList,roleList);
    }
}
