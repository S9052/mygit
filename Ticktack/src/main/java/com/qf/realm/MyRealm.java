package com.qf.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.qf.dao.IAuthorityDao;
import com.qf.dao.IUserDao;
import com.qf.entity.User;

public class MyRealm extends AuthorizingRealm{
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IAuthorityDao authoDao;

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		// 获取输入的账号
		String no = (String)principals.getPrimaryPrincipal();
		
		// 从数据库获取用户的角色和权限列表
		List<String> roleList = authoDao.findRoleByNo(no);
		List<String> permitList = authoDao.findTitleByNo(no);
		
		Set<String> roleSet = new HashSet<>(roleList);
		Set<String> perSet = new HashSet<>(permitList);
				
		// 授权信息的对象
		SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
		// 设置用户对应的角色
		info.setRoles(roleSet);
		// 设置对应的权限
		info.setStringPermissions(perSet);
		
		return info;
	}

	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		
		// 获取token中的账号
		String no = (String)token.getPrincipal();
		
		// 从数据库中根据账号获取密码信息
		User user = userDao.findByNo(no);
		if(user == null){
			throw new UnknownAccountException("此账号不存在");
		}
		String password = user.getPassword();
		
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(no, password, this.getName());
		
		return info;
	}

}
