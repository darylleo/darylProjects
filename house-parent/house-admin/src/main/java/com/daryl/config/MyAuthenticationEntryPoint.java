package com.daryl.config;

import com.daryl.domain.dtos.ResponseResult;
import com.daryl.domain.enums.HttpCodeEnum;
import com.daryl.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败自定义异常处理器
 * @author wl
 * @create 2022-02-15
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        WebUtils.renderString(response, new ResponseResult<>(HttpCodeEnum.FORBIDDEN.getCode(),request.getRequestURI()+e.getMessage()));
    }
}
