package com.mye.util;

import com.mye.entity.User;
import org.apache.shiro.SecurityUtils;

/**
 * @author zb
 * @date 2019-09-04 18:20
 **/
public class SecurityUtil {

    /**
     * 获取登录用户
     */
    public static User getCurrentUser() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 获取登录用户名
     */
    public static String getUsername() {
        User currentUser = getCurrentUser();
        return currentUser == null ? null : currentUser.getUsername();
    }

    /**
     * 获取当前用户的domain
     */
    public static String getDomain() {
        User currentUser = getCurrentUser();
        return currentUser == null ? null : currentUser.getDomain();
    }

}
