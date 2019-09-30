package com.mye.dto;

import com.mye.entity.Role;
import org.springframework.beans.BeanUtils;

/**
 * @author zb
 * @date 2019-09-04 16:40
 **/
public class RoleDTO {

    private Integer id;
    private String name;
    private String displayName;
    private String description;

    public static RoleDTO of(Role role) {
        if (role == null) {
            return null;
        }
        RoleDTO dto = new RoleDTO();
        BeanUtils.copyProperties(role, dto);
        return dto;
    }

    @Override
    public String toString() {
        return "RoleDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
