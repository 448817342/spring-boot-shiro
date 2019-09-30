package com.mye.service;

import com.mye.dto.PermissionDTO;
import com.mye.dto.add.AddPermission;
import com.mye.dto.update.UpdatePermission;
import com.mye.entity.Permission;
import com.mye.entity.User;
import com.mye.repo.PermissionRepository;
import com.mye.util.BeanUtil;
import com.mye.util.SecurityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zb
 * @date 2019-09-04 16:32
 **/
@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    /**
     * 检查权限
     *
     * @param id id
     */
    public Permission checkId(Integer id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("权限不存在！"));
    }

    /**
     * 权限树
     */
    public List<PermissionDTO> permissionList() {
        User user = SecurityUtil.getCurrentUser();
        List<Permission> permissionList = permissionRepository.findByUserId(user.getId());
        List<PermissionDTO> collect = permissionList.stream().map(PermissionDTO::of).collect(Collectors.toList());
        return PermissionDTO.parsePermissionTree(collect);
    }

    /**
     * 添加权限数据
     */
    public PermissionDTO add(AddPermission addPermission) {
        Permission permission = new Permission();
        BeanUtils.copyProperties(addPermission, permission);
        permissionRepository.save(permission);
        return PermissionDTO.of(permission);
    }

    /**
     * 修改权限数据
     */
    public PermissionDTO update(UpdatePermission updatePermission) {
        Permission permission = checkId(updatePermission.getId());
        BeanUtil.copyNotNullProperties(updatePermission, permission);
        permissionRepository.save(permission);
        return PermissionDTO.of(permission);
    }

    /**
     * 删除权限
     */
    public void delete(Set<Integer> ids) {
        ids.forEach(permissionRepository::deleteById);
    }
}
