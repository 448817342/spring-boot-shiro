package com.mye.dto;

import com.mye.entity.User;
import org.springframework.beans.BeanUtils;

/**
 * @author zb
 * @date 2019-08-31 17:49
 **/
public class UserDTO {

    private Integer id;
    private String username;
    private String cnName;
    private String mobile;
    private String domain;

    public static UserDTO of(User user) {
        if (user == null) {
            return null;
        }
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", cnName='" + cnName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", domain='" + domain + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
