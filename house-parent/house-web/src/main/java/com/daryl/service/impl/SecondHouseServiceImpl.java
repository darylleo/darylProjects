package com.daryl.service.impl;

import com.daryl.domain.constants.ExceptionMsg;
import com.daryl.domain.dtos.QueryParamDTO;
import com.daryl.service.SecondHouseService;
import com.daryl.customtemplate.MyException;
import com.daryl.domain.entity.SecondHouseEntity;
import com.daryl.mapper.SecondHouseMapper;
import com.daryl.utils.EntityUtil;
import com.daryl.utils.user.UserInfoUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * 二手房源实现类
 * @author wl
 * @create 2022-02-08
 */
@Service
public class SecondHouseServiceImpl implements SecondHouseService {
    @Resource
    private SecondHouseMapper secondHouseMapper;

    @Override
    public void addHouseInfo(SecondHouseEntity secondHouseEntity) {
        Example example = new Example(SecondHouseEntity.class);
        example.createCriteria().andEqualTo("detailedAddress", secondHouseEntity.getDetailedAddress());
        SecondHouseEntity localHouseData = secondHouseMapper.selectOneByExample(example);
        if (localHouseData != null) {
            throw MyException.error(ExceptionMsg.HOUSE_EXIST);
        }
        secondHouseEntity.setUserId(UserInfoUtil.getUserId());
        secondHouseEntity.setPublishRole(UserInfoUtil.getListRole().get(0));
        secondHouseMapper.insert( EntityUtil.addCreateInfo(secondHouseEntity));
    }

    @Override
    public void updateHouseInfo(SecondHouseEntity secondHouseEntity) {
        secondHouseMapper.updateByPrimaryKey(EntityUtil.addModifyInfo(secondHouseEntity));
    }

    @Override
    public void removeHouseInfo(SecondHouseEntity secondHouseEntity) {
        if (secondHouseEntity.getId() == null){
            throw MyException.error(ExceptionMsg.ID_IS_NULL);
        }
        secondHouseMapper.deleteById(secondHouseEntity.getId());
    }

    @Override
    public List<SecondHouseEntity> queryUserHouseInfo(SecondHouseEntity secondHouseEntity) {
        if (secondHouseEntity.getUserId() == null) {
            return new ArrayList<>();
        }
        return secondHouseMapper.queryUserHouseByUserId(secondHouseEntity.getUserId());
    }


    @Override
    public SecondHouseEntity queryHouseInfoById(SecondHouseEntity secondHouseEntity) {
        if (Objects.isNull(secondHouseEntity.getId())){
            throw MyException.error(ExceptionMsg.ID_IS_NULL);
        }

        return secondHouseMapper.selectById(secondHouseEntity.getId());
    }

    @Override
    public PageInfo<SecondHouseEntity> queryHouseInfoList(QueryParamDTO queryParamDTO) {
        //todo 查询条件没有写入
        PageHelper.startPage(queryParamDTO.getPageNum(),queryParamDTO.getPageSize());
        HashMap<String, Object> paramMap = queryParamDTO.getParamMap();
        return new PageInfo<>(secondHouseMapper.queryHouseInfoList());
    }
}
