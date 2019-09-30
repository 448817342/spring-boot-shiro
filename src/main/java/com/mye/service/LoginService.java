package com.mye.service;

import com.mye.common.ApiError;
import com.mye.common.ApiResponse;
import com.mye.dto.UserDTO;
import com.mye.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;


/**
 * @author zb
 * @date 2019-08-31 18:12
 **/
@Service
public class LoginService {

    public ApiResponse<?> login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException e) {
            return new ApiResponse<>("01", "密码错误");
        } catch (LockedAccountException e) {
            return new ApiResponse<>("03", "登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            return new ApiResponse<>("01", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return new ApiResponse<>(ApiError.OK, UserDTO.of(user));
    }

}
