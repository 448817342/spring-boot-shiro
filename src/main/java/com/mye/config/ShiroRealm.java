package com.mye.config;

import com.mye.common.Const;
import com.mye.entity.Permission;
import com.mye.entity.User;
import com.mye.repo.PermissionRepository;
import com.mye.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zb
 * @date 2019-08-31 16:31
 **/
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionRepository permissionRepository;

    /**
     * 接口访问授权，主要用来进行用户权限认证
     *
     * @param principals 权限拦截校验
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        // 查询当前用户的所有权限数据
        List<Permission> permissions = permissionRepository.findByUserId(user.getId());
        // 加入权限认证
        Set<String> collect = permissions.stream().filter(permission -> Const.PERMISSION_TYPE_OPERATION.equalsIgnoreCase(permission.getType()))
                .map(Permission::getUrl).collect(Collectors.toSet());

        collect.removeAll(Collections.singleton(null));
        if (!CollectionUtils.isEmpty(collect)) {
            authorizationInfo.addStringPermissions(collect);
        }

        return authorizationInfo;
    }

    /**
     * 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。
     *
     * @param token 请求认证令牌
     * @return 返回认证信息
     * @throws AuthenticationException 认证失败
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User user = userService.checkUsername(username);

        if (user == null) {
            throw new AuthenticationException("用户暂未注册！");
        }
        return new SimpleAuthenticationInfo(
                // 用户信息
                user,
                // 密码
                user.getPassword(),
                // 盐值加密 salt = username + salt
                ByteSource.Util.bytes(user.getSalt()),
                getName()
        );
    }
}
