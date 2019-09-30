package com.mye.service;

import com.mye.dto.UserDTO;
import com.mye.dto.add.AddUser;
import com.mye.dto.query.UserQuery;
import com.mye.dto.update.UpdateUser;
import com.mye.entity.User;
import com.mye.repo.UserRepository;
import com.mye.util.BeanUtil;
import com.mye.util.CryptoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Set;

/**
 * @author zb
 * @date 2019-08-31 16:47
 **/
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 检查用户是否存在
     *
     * @param username username
     * @return User
     */
    public User checkUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * 检查用户是否存在
     *
     * @param id userId
     */
    public User checkId(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("用户不存在！"));
    }

    /**
     * 用户列表查询
     *
     * @param pageable 分页参数
     * @param query    查询参数
     */
    public Page<UserDTO> userList(Pageable pageable, UserQuery query) {
        User user = new User();

        if (StringUtils.hasText(query.getUsername())) {
            user.setUsername(query.getUsername());
        }
        if (StringUtils.hasText(query.getMobile())) {
            user.setMobile(query.getMobile());
        }
        if (StringUtils.hasText(query.getCnName())) {
            user.setCnName(query.getCnName());
        }
        if (StringUtils.hasText(query.getDomain())) {
            user.setDomain(query.getDomain());
        }
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("domain", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("cn_name", ExampleMatcher.GenericPropertyMatchers.startsWith())
                .withMatcher("mobile", ExampleMatcher.GenericPropertyMatchers.exact())
                .withIgnorePaths("createTime", "updateTime");

        Page<User> userPage = userRepository.findAll(Example.of(user, matcher), pageable);

        return userPage.map(UserDTO::of);
    }

    /**
     * 添加用户
     */
    public UserDTO add(AddUser addUser) {
        User user = checkUsername(addUser.getUsername());
        if (user != null) {
            throw new IllegalArgumentException("用户已存在！");
        }

        user = new User();
        BeanUtils.copyProperties(addUser, user);
        user.setSalt(CryptoUtil.generateSalt());
        user.setPassword(CryptoUtil.generateMD5(addUser.getPassword(), user.getSalt()));

        userRepository.save(user);
        return UserDTO.of(user);
    }

    /**
     * 更新用户信息
     */
    public UserDTO update(UpdateUser updateUser) {
        User user = checkId(updateUser.getId());

        BeanUtil.copyNotNullProperties(updateUser, user);

        userRepository.save(user);
        return UserDTO.of(user);
    }

    /**
     * 删除用户
     */
    public void delete(Set<Integer> ids) {
        ids.forEach(userRepository::deleteById);
    }
}
