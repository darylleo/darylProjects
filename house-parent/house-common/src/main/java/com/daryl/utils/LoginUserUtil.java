package com.daryl.utils;

import com.daryl.domain.dtos.LoginUser;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author wl
 * @create 2022-02-08
 */
public class LoginUserUtil {
    public LoginUserUtil() {
    }

    public static LoginUser getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication instanceof AnonymousAuthenticationToken) {
                return null;
            }

            if (authentication instanceof AbstractAuthenticationToken) {
                return (LoginUser) authentication.getPrincipal();
            }
        }

        return null;
    }

    public static boolean isAuth(String authCode) {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null) {
            return false;
        } else {
            Collection<? extends GrantedAuthority> authorities = loginUser.getAuthorities();
            Iterator<?> it = authorities.iterator();

            GrantedAuthority grantedAuthority;
            do {
                if (!it.hasNext()) {
                    return false;
                }

                grantedAuthority = (GrantedAuthority)it.next();
            } while(!grantedAuthority.getAuthority().equals(authCode));

            return true;
        }
    }
}
