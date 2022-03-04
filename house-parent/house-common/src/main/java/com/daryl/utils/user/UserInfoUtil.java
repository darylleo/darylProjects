package com.daryl.utils.user;

import com.daryl.domain.dtos.LoginUser;
import com.daryl.utils.LoginUserUtil;

import java.util.List;

/**
 * @author wl
 * @create 2022-02-08
 */
public class UserInfoUtil {
    private UserInfoUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 获取登录用户id
     *
     * @return 登录用户id
     */
    public static Long getUserId() {
        LoginUser loginUser = LoginUserUtil.getLoginUser();
        if (loginUser == null) {
            return null;
        }
        return loginUser.getUserId();
    }

    /**
     * 获取登录用户用户名
     *
     * @return 登录用户id
     */
    public static String getUserName() {
        LoginUser loginUser = LoginUserUtil.getLoginUser();
        if (loginUser == null) {
            return null;
        }
        return loginUser.getUsername();
    }

    /**
     * 判断登录用户是否拥有该角色
     *
     * @param roleCode 角色编码
     * @return 是否拥有该角色
     */
    public static boolean haveRole(String roleCode) {
        LoginUser loginUser = LoginUserUtil.getLoginUser();
        if (loginUser == null) {
            return false;
        }
        List<String> roleList = loginUser.getRoleList();
        return roleList.contains(roleCode);
    }

    public static List<String> getListRole() {
        return LoginUserUtil.getLoginUser().getRoleList();
    }
}
