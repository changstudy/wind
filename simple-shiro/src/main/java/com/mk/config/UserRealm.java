package com.mk.config;

import com.mk.exception.ApiException;
import com.mk.exception.ErrorCode;
import com.mk.trandata.bo.UserInfo;
import com.mk.utils.AuthToken;
import com.mk.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import java.util.Arrays;

/**
 * @Author:ChenChang
 * @Description: <more hard, more luckier>
 * @Data: Create in 14:34 2023/3/1
 * @Modified By:
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof AuthToken;
    }

    /**
     * 后授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        long uid = Long.parseLong(subject.getPrincipal().toString());
        // TODO 获取权限
        simpleAuthorizationInfo.addStringPermissions(Arrays.asList("insert,update,delete,select".split(",")));
        return simpleAuthorizationInfo;
    }

    /**
     * 先认证
     * @param authToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
        //UsernamePasswordToken user = (UsernamePasswordToken) token;
        //String username = user.getUsername();
        //String password = String.valueOf(user.getPassword());
        // TODO token verify
        //if ("mkChang".equals(username) && "123456".equals(password)){
        //    MkUser mkUser = new MkUser();
        //    mkUser.setUsername(username);
        //    mkUser.setPassword(password);
        //    mkUser.setPerms("insert,update,delete,select"); // string List<String>
        //    return new SimpleAuthenticationInfo(mkUser, password, username);
        //}
        String token = authToken.getPrincipal().toString();
        UserInfo userInfo;
        try{
            userInfo = JwtService.getUserInfo(token);
        }catch (Exception e){
            throw new AuthenticationException("验证失败");
        }
        return new SimpleAuthenticationInfo(userInfo.getUid(), token, userInfo.getUsername());
    }
}
