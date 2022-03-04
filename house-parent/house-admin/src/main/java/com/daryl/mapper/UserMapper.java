package com.daryl.mapper;




import com.daryl.domain.entity.user.UserEntity;
import com.daryl.customtemplate.mapper.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户
 * @author wl
 * @create 2022-01-13
 */
public interface UserMapper extends MyMapper<UserEntity> {
    /**
     * 通过用户名查询用户
     * @param username 用户名
     * @return 用户信息
     */
    UserEntity queryByUsername(@Param("username") String username);

    /**
     * 条件查询所有用户
     * @param userEntity 查询条件封装
     * @return 用户信息
     */
    List<UserEntity> queryAllUsers(@Param("param") UserEntity userEntity);
}
