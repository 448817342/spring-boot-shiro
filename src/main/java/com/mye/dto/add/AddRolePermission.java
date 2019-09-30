package com.mye.dto.add;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @author zb
 * @date 2019-09-05 17:17
 **/
public class AddRolePermission {

    @Min(value = 1)
    private Integer roleId;
    @Size(min = 1)
    private Set<Integer> permissionIds;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Set<Integer> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(Set<Integer> permissionIds) {
        this.permissionIds = permissionIds;
    }
}
