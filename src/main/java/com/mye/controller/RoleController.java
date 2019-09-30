package com.mye.controller;

import com.mye.common.ApiError;
import com.mye.common.ApiResponse;
import com.mye.common.Const;
import com.mye.dto.RoleDTO;
import com.mye.dto.add.AddRole;
import com.mye.dto.add.AddRolePermission;
import com.mye.dto.update.UpdateRole;
import com.mye.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

/**
 * @author zb
 * @date 2019-09-04 16:28
 **/
@RestController
@RequestMapping(Const.API_VERSIONS_V1 + "/role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 角色列表
     */
    @RequiresPermissions(Const.API_VERSIONS_V1 + "/role/get")
    @GetMapping(value = "/list")
    public ApiResponse<?> roleList(@PageableDefault Pageable pageable) {
        return new ApiResponse<>(ApiError.OK, roleService.roleList(pageable));
    }

    /**
     * 角色详情
     */
    @RequiresPermissions(Const.API_VERSIONS_V1 + "/role/get")
    @GetMapping(value = "/{id}")
    public ApiResponse<?> getRoleInfo(@PathVariable Integer id) {
        return new ApiResponse<>(ApiError.OK, RoleDTO.of(roleService.checkId(id)));
    }

    /**
     * 添加角色
     */
    @RequiresPermissions(Const.API_VERSIONS_V1 + "/role/add")
    @PostMapping
    public ApiResponse<?> add(@RequestBody @Valid AddRole addRole) {
        return new ApiResponse<>(ApiError.OK, roleService.add(addRole));
    }

    /**
     * 更新角色
     */
    @RequiresPermissions(Const.API_VERSIONS_V1 + "/role/update")
    @PutMapping
    public ApiResponse<?> update(@RequestBody @Valid UpdateRole updateRole) {
        return new ApiResponse<>(ApiError.OK, roleService.update(updateRole));
    }

    /**
     * 删除角色
     */
    @RequiresPermissions(Const.API_VERSIONS_V1 + "/role/delete")
    @DeleteMapping
    public ApiResponse<?> delete(@RequestBody Set<Integer> ids) {
        roleService.delete(ids);
        return new ApiResponse<>(ApiError.OK);
    }

    /**
     * 角色关联权限
     */
    @RequiresPermissions(Const.API_VERSIONS_V1 + "/role/permission/add")
    @PostMapping(value = "/permission")
    public ApiResponse<?> addPermission(@RequestBody @Valid AddRolePermission addRolePermission) {
        roleService.addPermission(addRolePermission);
        return new ApiResponse<>(ApiError.OK);
    }

}
