package com.mye.dto.add;

import javax.validation.constraints.Size;

/**
 * @author zb
 * @date 2019-09-04 10:37
 **/
public class AddUser {

    @Size(min = 6, max = 16)
    private String username;
    private String cnName;
    @Size(min = 6, max = 16)
    private String password;
    @Size(min = 8, max = 15)
    private String mobile;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
