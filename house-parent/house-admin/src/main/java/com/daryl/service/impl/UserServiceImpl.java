package com.daryl.service.impl;


import com.daryl.domain.dto.RoleMenuDTO;
import com.daryl.domain.dto.UserRoleDTO;

import com.daryl.domain.dtos.ValidOptions;
import com.daryl.domain.entity.user.*;
import com.daryl.service.UserService;
import com.daryl.domain.constants.ExceptionMsg;

import com.daryl.domain.dtos.LoginUser;
import com.daryl.domain.dtos.QueryParamDTO;

import com.daryl.customtemplate.MyException;

import com.daryl.domain.constants.GlobalField;

import com.daryl.domain.enums.HttpCodeEnum;


import com.daryl.mapper.*;
import com.daryl.utils.*;
import com.daryl.utils.user.UserInfoUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wl
 * @create 2022-01-13
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private MenuMapper menuMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;


    @Override
    public void createUser(UserEntity userEntity) {
        UserEntity user = userMapper.queryByUsername(userEntity.getUsername());
        if (user != null) {
            throw new MyException(ExceptionMsg.SYS_USERNAME_EXIST);
        }
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        userMapper.insert(EntityUtil.addCreateInfo(userEntity));
    }

    @Override
    public void updateUser(UserEntity userEntity) {
        UserEntity user = userMapper.queryByUsername(userEntity.getUsername());
        if (user != null && user.getUsername().equals(userEntity.getUsername()) && user.getId().equals(userEntity.getId())) {
            throw MyException.error(ExceptionMsg.SYS_USERNAME_EXIST);
        }
        userMapper.updateByPrimaryKey(EntityUtil.addModifyInfo(userEntity));
    }

    @Override
    public void deleteUser(UserEntity userEntity) {
        if (Objects.isNull(userEntity.getId())) {
            throw MyException.error(ExceptionMsg.ID_IS_NULL);
        }
        userMapper.deleteById(userEntity.getId());
        //逻辑删除关联表
        userRoleMapper.removeUserRoleByUserId(userEntity.getId(), UserInfoUtil.getUserName());
    }

    @Override
    public String login(UserEntity userEntity) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userEntity.getUsername(), userEntity.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (Objects.isNull(authenticate)) {
            throw MyException.error(HttpCodeEnum.LOGIN_FAILED.getMsg());
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String id = String.valueOf(loginUser.getUserId());
        String token = JwtUtil.createJWT(id);
        try {
            redisCache.setCacheObject(GlobalField.LOGIN + id, loginUser);
        } catch (Exception e) {
            e.printStackTrace();
            throw MyException.error(ExceptionMsg.REDIS_SERVER_ERROR);
        }
        return token;
    }

    @Override
    public void logout() {
        //获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser principal = (LoginUser) authentication.getPrincipal();

        //删除redis中的值
        redisCache.deleteObject(GlobalField.LOGIN + principal.getUserId());
    }

    @Override
    public PageInfo<UserEntity> queryAllUsers(QueryParamDTO queryParamDTO) {
        PageHelper.startPage(queryParamDTO.getPageNum(), queryParamDTO.getPageSize());
        HashMap<String, Object> paramMap = queryParamDTO.getParamMap();
        Example example = new Example(UserEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("del_tag", GlobalField.LOGIC_DELETE_FALSE);
        if (Objects.nonNull(paramMap.get("username"))) {
            criteria.andLike("username", SqlUtils.andLike((String) paramMap.get("username")));
        }
        //todo 应该不止这点查询条件
        return new PageInfo<>(userMapper.selectByExample(example));
    }

    @Override
    public void createRole(RoleEntity roleEntity) {
        Example example = new Example(RoleEntity.class);
        example.createCriteria().andEqualTo("roleCode", roleEntity.getRoleCode());
        RoleEntity localRole = roleMapper.selectOneByExample(example);
        if (Objects.isNull(roleEntity.getId())) {
            if (Objects.isNull(localRole)) {
                roleMapper.insert(EntityUtil.addCreateInfo(roleEntity));
            } else {
                throw MyException.error(ExceptionMsg.SYS_ROLE_CODE_EXIST);
            }
        } else {
            if (Objects.isNull(localRole) || (roleEntity.getId().equals(localRole.getId()))) {
                roleMapper.updateByPrimaryKey(EntityUtil.addModifyInfo(roleEntity));
            } else {
                throw MyException.error(ExceptionMsg.SYS_ROLE_CODE_EXIST);
            }
        }
    }

    @Override
    public void updateRole(RoleEntity roleEntity) {

    }

    @Override
    public void deleteRole(RoleEntity roleEntity) {
        if (Objects.isNull(roleEntity.getId())) {
            throw MyException.error(ExceptionMsg.ID_IS_NULL);
        }
        roleMapper.deleteById(roleEntity.getId());
        //删除关联表信息
        roleMenuMapper.removeRoleMenuByRoleId(roleEntity.getId(), UserInfoUtil.getUserName());
        userRoleMapper.removeUserRoleByUserId(roleEntity.getId(), UserInfoUtil.getUserName());
    }

    @Override
    public PageInfo<RoleEntity> queryAllRoles(QueryParamDTO queryParamDTO) {
        PageHelper.startPage(queryParamDTO.getPageNum(), queryParamDTO.getPageSize());
        Example example = new Example(RoleEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("del_tag", GlobalField.LOGIC_DELETE_FALSE);
        HashMap<String, Object> paramMap = queryParamDTO.getParamMap();
        if (Objects.nonNull(paramMap.get("roleName"))) {
            criteria.andLike("roleName", SqlUtils.andLike((String) paramMap.get("roleName")));
        }
        if (Objects.nonNull(paramMap.get("roleCode"))) {
            criteria.andLike("roleCode", SqlUtils.andLike((String) paramMap.get("roleCode")));
        }
        if (Objects.nonNull(paramMap.get("status"))) {
            criteria.andEqualTo("status", paramMap.get("status"));
        }
        return new PageInfo<>(roleMapper.selectByExample(example));
    }

    @Override
    public void createMenu(MenuEntity menuEntity) {
        Example example = new Example(MenuEntity.class);
        example.createCriteria().andEqualTo("menuCode", menuEntity.getMenuCode());
        MenuEntity localMenu = menuMapper.selectOneByExample(example);
        if (Objects.isNull(menuEntity.getId())) {
            if (Objects.isNull(localMenu)) {
                menuEntity.setStatus(GlobalField.ENTITY_STATUS_UP);
                menuMapper.insert(EntityUtil.addCreateInfo(menuEntity));
            } else {
                throw MyException.error(ExceptionMsg.SYS_MENU_CODE_EXIST);
            }
        } else {
            if (Objects.isNull(localMenu) || (menuEntity.getId().equals(localMenu.getId()))) {
                menuMapper.updateByPrimaryKey(EntityUtil.addModifyInfo(menuEntity));
            } else {
                throw MyException.error(ExceptionMsg.SYS_MENU_CODE_EXIST);
            }
        }
    }

    @Override
    public void updateMenu(MenuEntity menuEntity) {

    }

    @Override
    public void deleteMenu(MenuEntity menuEntity) {
        if (Objects.isNull(menuEntity.getId())) {
            throw MyException.error(ExceptionMsg.ID_IS_NULL);
        }
        menuMapper.deleteById(menuEntity.getId());
        //删除关联表
        roleMenuMapper.removeRoleMenuByMenuId(menuEntity.getId(), UserInfoUtil.getUserName());

    }

    @Override
    public PageInfo<MenuEntity> queryAllMenus(QueryParamDTO queryParamDTO) {
        PageHelper.startPage(queryParamDTO.getPageNum(), queryParamDTO.getPageSize());
        HashMap<String, Object> paramMap = queryParamDTO.getParamMap();
        Example example = new Example(MenuEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("del_tag", GlobalField.LOGIC_DELETE_FALSE);
        if (Objects.nonNull(paramMap.get("menuName"))) {
            criteria.andLike("menuName", SqlUtils.andLike((String) paramMap.get("menuName")));
        }
        if (Objects.nonNull(paramMap.get("menuCode"))) {
            criteria.andLike("menuCode", SqlUtils.andLike((String) paramMap.get("menuCode")));
        }
        if (Objects.nonNull(paramMap.get("status"))) {
            criteria.andEqualTo("status", paramMap.get("status"));
        }
        return new PageInfo<>(menuMapper.selectByExample(example));
    }

    @Override
    public void bindRolesToUser(UserRoleDTO userRoleDTO) {
        //先删除该用户原先的角色
        userRoleMapper.removeUserRoleByUserId(userRoleDTO.getUserId(), UserInfoUtil.getUserName());
        //为用户绑定角色
        if (Objects.nonNull(userRoleDTO.getRoleList())) {
            userRoleDTO.getRoleList()
                    .forEach(roleEntity -> userRoleMapper.insert(EntityUtil.addCreateInfo(new UserRoleEntity(roleEntity.getId(), userRoleDTO.getUserId()))));
        }
    }

    @Override
    public void bindMenusToRole(RoleMenuDTO roleMenuDto) {
        //删除该角色原先的权限信息
        roleMenuMapper.removeRoleMenuByRoleId(roleMenuDto.getRoleId(), UserInfoUtil.getUserName());
        //给角色绑定权限
        if (Objects.nonNull(roleMenuDto.getMenuList())) {
            roleMenuDto.getMenuList()
                    .forEach(menuEntity -> roleMenuMapper.insert(EntityUtil.addCreateInfo(new RoleMenuEntity(roleMenuDto.getRoleId(), menuEntity.getId()))));
        }
    }

    @Override
    public void createRegisterUser(UserEntity userEntity) {
        UserEntity user = userMapper.queryByUsername(userEntity.getUsername());
        if (Objects.nonNull(user)) {
            throw MyException.error(ExceptionMsg.SYS_USERNAME_EXIST);
        }
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        userEntity.setCreateBy(userEntity.getUsername());
        userMapper.insert(EntityUtil.addCreateInfo(userEntity));
        List<RoleEntity> roleList = roleMapper.queryRoleByRoleCode("REGISTER");
        if (roleList.isEmpty()) {
            throw MyException.error(ExceptionMsg.SYS_ROLE_INFO_UN_EXIST);
        }
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUserId(userMapper.queryByUsername(userEntity.getUsername()).getId());
        userRoleEntity.setRoleId(roleList.get(0).getId());
        userRoleMapper.insert(EntityUtil.addCreateInfo(userRoleEntity));
    }

    //绑定权限时，如果不是直接删除原先绑定的解决方案
    private void bindRole(RoleMenuDTO roleMenuDTO) {
        //现在要加进来的权限
        List<Long> newMenu = roleMenuDTO.getMenuList().stream().map(MenuEntity::getId).collect(Collectors.toList());
        //找出该角色原先的权限
        List<RoleMenuEntity> roleMenuEntities = roleMenuMapper.queryRoleMenuByRoleId(roleMenuDTO.getRoleId());
        List<Long> oldMenu = roleMenuEntities.stream().map(RoleMenuEntity::getMenuId).collect(Collectors.toList());

        if (oldMenu.isEmpty()) {
            //第一次配置权限，直接添加
            newMenu.forEach(menuId -> roleMenuMapper.insert(EntityUtil.addCreateInfo(new RoleMenuEntity(roleMenuDTO.getRoleId(), menuId))));
        } else {
            if (newMenu.containsAll(oldMenu)) {
                //如果新添全包旧的，那么只添加新增的
                findLatest(oldMenu, newMenu).forEach(menuId -> roleMenuMapper.insert(EntityUtil.addCreateInfo(new RoleMenuEntity(roleMenuDTO.getRoleId(), menuId))));
                return;
            }
            //找出相同的
            ArrayList<Long> sameMenu = new ArrayList<>();
            oldMenu.forEach(menuId -> {
                if (newMenu.contains(menuId)) {
                    sameMenu.add(menuId);
                }
            });
            //没有相同的，原先的全部删除，并添加最新的
            if (sameMenu.isEmpty()) {
                roleMenuMapper.removeRoleMenuByRoleId(roleMenuDTO.getRoleId(), UserInfoUtil.getUserName());
                newMenu.forEach(menuId -> roleMenuMapper.insert(EntityUtil.addCreateInfo(new RoleMenuEntity(roleMenuDTO.getRoleId(), menuId))));
            } else {
                //有相同，有点小难啊   还是直接把旧的删光  新的全加  比较简单
                //要删除  旧的中 不相同的部分， 要新增  新的中  不相同的部分
                sameMenu.forEach(menuId -> roleMenuEntities.removeIf(roleMenuEntity -> roleMenuEntity.getMenuId().equals(menuId)));
                //oldMenu.forEach();
                roleMenuEntities.forEach(roleMenuEntity -> roleMenuMapper.deleteById(roleMenuEntity.getId()));
                findLatest(sameMenu, newMenu).forEach(menuId -> roleMenuMapper.insert(EntityUtil.addCreateInfo(new RoleMenuEntity(roleMenuDTO.getRoleId(), menuId))));
            }
        }
    }

    private List<Long> findLatest(List<Long> sameList, List<Long> newList) {
        sameList.forEach(newList::remove);
        return newList;
    }
}
