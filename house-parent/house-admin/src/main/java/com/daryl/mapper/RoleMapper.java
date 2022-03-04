package com.daryl.mapper;

import com.daryl.domain.entity.user.RoleEntity;
import com.daryl.customtemplate.mapper.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 角色
 * @author wl
 * @create 2022-02-10
 */
public interface RoleMapper extends MyMapper<RoleEntity> {

    /**
     * 通过角色编码查询角色信息
     * @param roleCode 角色编码
     * @return 角色信息
     */
    @Select("SELECT id,create_by,create_time,modify_by,modify_time,role_name,role_code,status FROM t_sys_role WHERE role_code = #{roleCode} AND del_tag = 0 AND status = 0" )
    List<RoleEntity> queryRoleByRoleCode(@Param("roleCode") String roleCode);
}
