package com.daryl.utils;


import com.daryl.utils.user.UserInfoUtil;
import com.daryl.domain.entity.MyEntity;

import java.util.Date;

/**
 * 实体类工具
 *
 * @author tz
 * @create 2021-08-06
 */
public class EntityUtil {

    private EntityUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 添加实体类创建信息
     *
     * @param entity 实体类
     */
    public static <T extends MyEntity> T addCreateInfo(T entity) {
/*        if (entity instanceof MyEntity) {
            ((MyEntity) entity).setCreateTime(new Date());
            ((MyEntity) entity).setCreateBy(UserInfoUtil.getUserName());
            ((MyEntity) entity).setModifyTime(null);
            ((MyEntity) entity).setModifyBy(null);
        }*/
        entity.setCreateTime(new Date());
        entity.setCreateBy(UserInfoUtil.getUserName());
        entity.setModifyTime(null);
        entity.setModifyBy(null);
        return entity;
    }

    /**
     * 添加实体类创建信息
     *
     * @param entity entity
     * @param <T>    entity
     * @return entity
     */
    public static <T extends MyEntity> T addModifyInfo(T entity) {
/*        if (entity instanceof MyEntity) {
            ((MyEntity) entity).setModifyTime(new Date());
            ((MyEntity) entity).setModifyBy(UserInfoUtil.getUserName());
        }*/
        entity.setModifyTime(new Date());
        entity.setModifyBy(UserInfoUtil.getUserName());
        return entity;
    }
}
