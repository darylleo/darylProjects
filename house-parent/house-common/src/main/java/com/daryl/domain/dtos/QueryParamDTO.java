package com.daryl.domain.dtos;

import lombok.Data;

import java.util.HashMap;

/**
 * 条件查询参数
 *
 * @author wl
 * @create 2022-02-10
 */
@Data
public class QueryParamDTO {
    /**
     * 当前页数（默认1）
     */
    private Integer pageNum = 1;

    /**
     * 每页条数（默认10）
     */
    private Integer pageSize = 10;

    /**
     * 存放查询参数
     */
    private HashMap<String, Object> paramMap = new HashMap<>();
}
