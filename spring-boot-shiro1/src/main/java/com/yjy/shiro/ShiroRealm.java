package com.yjy.shiro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yjy.domain.Permission;
import com.yjy.domain.Role;
import com.yjy.domain.User;
import com.yjy.service.UserService;

public class ShiroRealm extends AuthorizingRealm {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserService userService;

	//注：这个方法会多次被调用
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		logger.info("授权方法（doGetAuthorizationInfo）：" + principalCollection.toString());
		
		String username = (String) principalCollection.getPrimaryPrincipal();
		
		User user = userService.findByUsername(username);
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		List<Permission> permissions = new ArrayList<>();
		
		if(username.equals(Role.ADMIN_ROLE)) {
			
		}
		
		//赋予角色
		for(Role role : user.getRoles()) {
			permissions.addAll(role.getPermissions());
			info.addRole(role.getName());
		}
		//赋予权限
		for (Permission permission : permissions) {
			info.addStringPermission(permission.getCode());
		}
		
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		logger.info("认证方法（doGetAuthenticationInfo）：" + token.toString());
		
		
		
		
		String username = (String) token.getPrincipal();
		String password = new String((char[])token.getCredentials());  
		
		User user = userService.findByUsername(username);
		
		if(password.equals(user.getPassword())) {
			Session session = SecurityUtils.getSubject().getSession();
			session.setAttribute("currentUser", user);
			return new SimpleAuthenticationInfo(username, user.getPassword(), getName());
		}
		return null;
	}

}
