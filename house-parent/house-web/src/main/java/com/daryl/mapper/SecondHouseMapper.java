package com.daryl.mapper;

import com.daryl.customtemplate.mapper.MyMapper;
import com.daryl.domain.entity.SecondHouseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wl
 * @create 2022-02-08
 */
@Mapper
public interface SecondHouseMapper extends MyMapper<SecondHouseEntity> {

    /**
     * 查询房源详细信息
     *
     * @param userId 用户id
     * @return 房源详细信息
     */
    List<SecondHouseEntity> queryUserHouseByUserId(@Param("userId") Long userId);

    /**
     * 条件查询房源信息
     * @return
     */
    List<SecondHouseEntity> queryHouseInfoList();
}
