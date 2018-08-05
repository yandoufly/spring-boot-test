package com.yjy.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yjy.domain.Permission;
import com.yjy.domain.User;
import com.yjy.domain.UserRole;
import com.yjy.service.PermissionService;
import com.yjy.service.UserService;

public class ShiroRealm extends AuthorizingRealm {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private PermissionService permissionService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		logger.info("doGetAuthorizationInfo+" + principalCollection.toString());

		User user = userService.findByUserName((String) principalCollection.getPrimaryPrincipal());

		// 把principals放session中 key=userId value=principals
		SecurityUtils.getSubject().getSession().setAttribute(String.valueOf(user.getId()),
				SecurityUtils.getSubject().getPrincipals());
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 赋予角色
		for (UserRole userRole : user.getUserRoles()) {
			info.addRole(userRole.getRole().getName());
		}
		// 赋予权限
		for (Permission permission : permissionService.getPermissions(user.getId())) {
			// if(StringUtils.isNotBlank(permission.getPermCode()))
			info.addStringPermission(permission.getPermCode());
		}
		// 设置登录次数、时间
		// userService.updateUserLogin(user);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		logger.info("doGetAuthenticationInfo +" + authenticationToken.toString());

		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		String username = token.getUsername();
		logger.info(username + token.getPassword());

		User user = userService.findByUserName(token.getUsername());
		if (user != null) {
			Session session = SecurityUtils.getSubject().getSession();
			session.setAttribute("user", user);
			return new SimpleAuthenticationInfo(username, user.getPassword(), getName());
		}
		return null;
	}

}
