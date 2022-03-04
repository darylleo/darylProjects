package com.daryl.filters;


import com.daryl.domain.dtos.LoginUser;
import com.daryl.domain.dtos.ResponseResult;
import com.daryl.domain.enums.HttpCodeEnum;
import com.daryl.utils.JwtUtil;
import com.daryl.utils.RedisCache;
import com.daryl.utils.WebUtils;
import com.daryl.domain.constants.GlobalField;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 自定义jwt认证过滤器
 *
 * @author wl
 * @create 2022-01-27
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    /*    @Autowired
        private RedisClient redisClient;*/
    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.contains(request.getRequestURI(), "login")) {
            filterChain.doFilter(request, response);
            return;
        }
        //获取token
        String token = request.getHeader(GlobalField.TOKEN);
        if (StringUtils.isBlank(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        //解析token
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            //无效的token
            WebUtils.renderString(response, ResponseResult.errorResult(HttpCodeEnum.TOKEN_INVALID.getMsg()));
            return;
        }
        //从redis中获取用户信息
        LoginUser loginUser = redisCache.getCacheObject(GlobalField.LOGIN + userId);
        if (Objects.isNull(loginUser)) {
            WebUtils.renderString(response, ResponseResult.errorResult(HttpCodeEnum.LOGOUT.getMsg()));
            return;
            //throw MyException.error("用户已登出");
        }
        //存入securityContextHolder
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
