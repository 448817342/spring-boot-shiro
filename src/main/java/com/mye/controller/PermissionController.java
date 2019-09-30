package com.mye.controller;

import com.mye.common.ApiError;
import com.mye.common.ApiResponse;
import com.mye.common.Const;
import com.mye.dto.PermissionDTO;
import com.mye.dto.add.AddPermission;
import com.mye.dto.update.UpdatePermission;
import com.mye.service.PermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

/**
 * @author zb
 * @date 2019-09-04 16:28
 **/
@RestController
@RequestMapping(Const.API_VERSIONS_V1 + "/permission")
public class PermissionController {

    private final PermissionService permissionService;

    @Autowired
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    /**
     * 权限列表查询
     */
    @RequiresPermissions(Const.API_VERSIONS_V1 + "/permission/get")
    @GetMapping(value = "/list")
    public ApiResponse<?> permissionList() {
        return new ApiResponse<>(ApiError.OK, permissionService.permissionList());
    }

    /**
     * 权限详情查询
     *
     * @param id 权限id
     */
    @RequiresPermissions(Const.API_VERSIONS_V1 + "/permission/get")
    @GetMapping(value = "/{id}")
    public ApiResponse<?> getPermissionInfo(@PathVariable Integer id) {
        return new ApiResponse<>(ApiError.OK, PermissionDTO.of(permissionService.checkId(id)));
    }

    /**
     * 添加权限
     *
     * @param addPermission 权限参数
     */
    @RequiresPermissions(Const.API_VERSIONS_V1 + "/permission/add")
    @PostMapping
    public ApiResponse<?> add(@RequestBody @Valid AddPermission addPermission) {
        return new ApiResponse<>(ApiError.OK, permissionService.add(addPermission));
    }

    /**
     * 修改权限数据
     */
    @RequiresPermissions(Const.API_VERSIONS_V1 + "/permission/update")
    @PutMapping
    public ApiResponse<?> update(@RequestBody @Valid UpdatePermission updatePermission) {
        return new ApiResponse<>(ApiError.OK, permissionService.update(updatePermission));
    }

    /**
     * 删除权限
     *
     * @param ids 权限id集
     */
    @RequiresPermissions(Const.API_VERSIONS_V1 + "/permission/delete")
    @DeleteMapping
    public ApiResponse<?> delete(@RequestBody Set<Integer> ids) {
        permissionService.delete(ids);
        return new ApiResponse<>(ApiError.OK);
    }

}
