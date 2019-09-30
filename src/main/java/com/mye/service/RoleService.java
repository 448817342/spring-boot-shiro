package com.mye.service;

import com.mye.dto.RoleDTO;
import com.mye.dto.add.AddRole;
import com.mye.dto.add.AddRolePermission;
import com.mye.dto.update.UpdateRole;
import com.mye.entity.Role;
import com.mye.entity.RolePermission;
import com.mye.repo.RolePermissionRepository;
import com.mye.repo.RoleRepository;
import com.mye.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author zb
 * @date 2019-09-04 16:31
 **/
@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final RolePermissionRepository rolePermissionRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository, RolePermissionRepository rolePermissionRepository) {
        this.roleRepository = roleRepository;
        this.rolePermissionRepository = rolePermissionRepository;
    }

    public Role checkId(Integer id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("角色不存在！"));
    }

    /**
     * 角色列表数据
     *
     * @param pageable 分页参数
     */
    public Page<RoleDTO> roleList(Pageable pageable) {
        Page<Role> rolePage = roleRepository.findAll(pageable);
        return rolePage.map(RoleDTO::of);
    }

    /**
     * 添加角色数据
     */
    public RoleDTO add(AddRole addRole) {
        Role role = new Role();
        BeanUtils.copyProperties(addRole, role);
        roleRepository.save(role);
        return RoleDTO.of(role);
    }

    /**
     * 更新角色数据
     */
    public RoleDTO update(UpdateRole updateRole) {
        Role role = checkId(updateRole.getId());

        BeanUtil.copyNotNullProperties(updateRole, role);
        roleRepository.save(role);
        return RoleDTO.of(role);
    }

    /**
     * 删除角色数据
     */
    public void delete(Set<Integer> ids) {
        ids.forEach(roleRepository::deleteById);
    }

    /**
     * 角色关联权限
     */
    public void addPermission(AddRolePermission addRolePermission) {
        addRolePermission.getPermissionIds().forEach(id -> {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(addRolePermission.getRoleId());
            rolePermission.setPermissionId(id);
            rolePermissionRepository.save(rolePermission);
        });
    }
}
