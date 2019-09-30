package com.mye.controller;

import com.mye.common.ApiError;
import com.mye.common.ApiResponse;
import com.mye.common.Const;
import com.mye.dto.UserDTO;
import com.mye.dto.add.AddUser;
import com.mye.dto.query.UserQuery;
import com.mye.dto.update.UpdateUser;
import com.mye.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

/**
 * @author zb
 * @date 2019-09-04 10:36
 **/
@RestController
@RequestMapping(Const.API_VERSIONS_V1 + "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户列表
     *
     * @param pageable 分页参数
     * @param query    查询参数
     */
    @GetMapping(value = "/list")
    public ApiResponse<?> userList(@PageableDefault Pageable pageable, UserQuery query) {
        return new ApiResponse<>(ApiError.OK, userService.userList(pageable, query));
    }

    /**
     * 用户详情
     *
     * @param id 用户id
     */
    @GetMapping(value = "/{id}")
    public ApiResponse<?> getUserInfo(@PathVariable Integer id) {
        return new ApiResponse<>(ApiError.OK, UserDTO.of(userService.checkId(id)));
    }

    /**
     * 添加用户
     *
     * @param addUser 新用户参数
     */
    @RequiresPermissions(Const.API_VERSIONS_V1 + "/user/add")
    @PostMapping
    public ApiResponse<?> add(@RequestBody @Valid AddUser addUser) {
        return new ApiResponse<>(ApiError.OK, userService.add(addUser));
    }

    /**
     * 更新用户信息
     *
     * @param updateUser 修改用户信息
     */
    @RequiresPermissions(Const.API_VERSIONS_V1 + "/user/update")
    @PutMapping
    public ApiResponse<?> update(@RequestBody @Valid UpdateUser updateUser) {
        return new ApiResponse<>(ApiError.OK, userService.update(updateUser));
    }

    /**
     * 删除用户
     *
     * @param ids 用户id
     */
    @RequiresPermissions(Const.API_VERSIONS_V1 + "/user/delete")
    @DeleteMapping
    public ApiResponse<?> delete(@RequestBody Set<Integer> ids) {
        userService.delete(ids);
        return new ApiResponse<>(ApiError.OK);
    }

}
