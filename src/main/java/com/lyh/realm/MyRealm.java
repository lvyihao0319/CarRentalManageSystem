package com.lyh.realm;

import javax.annotation.Resource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import com.lyh.bean.User;
import com.lyh.service.UserServiceInf;



/**
 * 自定义Realm
 * @author Administrator
 *
 */
public class MyRealm extends AuthorizingRealm{

	@Resource
	private UserServiceInf  userServiceInf;
	/**
	 * 验证当前登录的用户
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		String login_type = (String) SecurityUtils.getSubject().getSession().getAttribute("login_type");
		switch (login_type) {
		
		case "user_login":
			String num=(String) token.getPrincipal();//我上面使用了num取到用户的登录名
			User user=userServiceInf.findByNum(num);
			
			if(user!=null){
				//SecurityUtils.getSubject().getSession().setAttribute("currentUser", user); //把当前用户信息存到session中
				AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(user.getNum_(), user.getPassword(), "xxx");
				return authcInfo;
			}else{
			}
			break;
		}
		return null;
	}
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

}
