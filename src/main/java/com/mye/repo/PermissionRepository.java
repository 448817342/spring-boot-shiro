package com.mye.repo;

import com.mye.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author zb
 * @date 2019-08-31 15:48
 **/
public interface PermissionRepository extends JpaRepository<Permission, Integer>, JpaSpecificationExecutor<Permission> {

    /**
     * 查询用户的所有权限
     *
     * @param userId userId
     * @return List<Permission>
     */
    @Query(nativeQuery = true, value = "select permission.* from user_role ur join role_permission rp on ur.role_id = rp.role_id " +
            "join permission on rp.permission_id = permission.id and ur.user_id = ?1")
    List<Permission> findByUserId(Integer userId);

}
