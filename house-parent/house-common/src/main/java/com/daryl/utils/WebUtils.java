package com.daryl.utils;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * web工具类
 *
 * @author wl
 * @create 2022-01-25
 */
public class WebUtils {

    /**
     * 将对象转成String输出
     *
     * @param response 响应
     * @param obj      要转String的对象
     */
    public static void renderString(HttpServletResponse response, Object obj) {
        try {
            //response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(JSON.toJSONString(obj));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
