package com.daryl.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * sql 工具类
 *
 * @author wl
 * @create 2022-02-11
 */
public class SqlUtils {
    private static final String percentSign = "%";


    /**
     * 模糊查询like
     *
     * @param str 模糊查询内容
     * @return 拼接后的模糊查询
     */
    public static String andLike(String str) {
        if (StringUtils.isNotBlank(str)) {
            return percentSign + str + percentSign;
        }
        return str;
    }
}
