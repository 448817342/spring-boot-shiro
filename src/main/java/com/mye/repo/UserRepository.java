package com.mye.repo;

import com.mye.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author zb
 * @date 2019-08-31 15:45
 **/
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    /**
     * 查询用户
     *
     * @param username username
     * @return User
     */
    User findByUsername(String username);
}
