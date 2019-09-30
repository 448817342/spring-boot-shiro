package com.mye.controller;

import com.mye.common.ApiResponse;
import com.mye.common.Const;
import com.mye.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zb
 * @date 2019-08-31 18:10
 **/
@RestController
@RequestMapping(Const.API_VERSIONS_V1)
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录信息
     */
    @PostMapping(value = "/login")
    public ApiResponse<?> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return loginService.login(username, password);
    }
}
