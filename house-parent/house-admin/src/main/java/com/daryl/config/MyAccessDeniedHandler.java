package com.daryl.config;

import com.daryl.domain.dtos.ResponseResult;
import com.daryl.domain.enums.HttpCodeEnum;
import com.daryl.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义授权失败异常处理器
 *
 * @author wl
 * @create 2022-02-15
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        WebUtils.renderString(response, new ResponseResult<>(HttpCodeEnum.FORBIDDEN.getCode(),"NO AUTHORITY TO "+ request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1) ));
    }
}
