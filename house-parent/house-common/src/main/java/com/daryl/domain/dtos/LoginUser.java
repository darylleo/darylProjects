package com.daryl.domain.dtos;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wl
 * @create 2022-01-24
 */
@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {
    /**
     * 用户信息
     */
    private UserInfoDto userInfoDto;

    /**
     * 权限信息
     */
    private List<String> menuList;

    /**
     * 角色信息
     */
    private List<String> roleList;

    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities != null) {
            return authorities;
        }
        authorities = menuList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        //System.out.println(authorities);
        return authorities;
    }


    public LoginUser(UserInfoDto userInfoDto, List<String> menuList ,List<String> roleList) {
        this.userInfoDto = userInfoDto;
        this.menuList = menuList;
        this.roleList = roleList;
    }

    @Override
    public String getPassword() {
        return userInfoDto.getPassword();
    }

    public long getUserId() {
        return userInfoDto.getId();
    }

    @Override
    public String getUsername() {
        return userInfoDto.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
