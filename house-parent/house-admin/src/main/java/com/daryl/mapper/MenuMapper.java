package com.daryl.mapper;


import com.daryl.domain.entity.user.MenuEntity;
import com.daryl.customtemplate.mapper.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限菜单
 *
 * @author wl
 * @create 2022-02-10
 */
public interface MenuMapper extends MyMapper<MenuEntity> {
    /**
     * 条件查询所有权限信息
     * @param menuName 权限名称
     * @param menuCode 权限代码
     * @param status 权限状态
     * @return 权限信息
     */
    List<MenuEntity> queryAllMenus(@Param("menuName") String menuName, @Param("menuCode") String menuCode, @Param("status") Integer status);
}
