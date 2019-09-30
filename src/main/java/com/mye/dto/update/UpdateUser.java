package com.mye.dto.update;

import javax.validation.constraints.Min;

/**
 * @author zb
 * @date 2019-09-04 11:38
 **/
public class UpdateUser {

    @Min(value = 1)
    private Integer id;
    private String cnName;
    private String password;
    private String mobile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
