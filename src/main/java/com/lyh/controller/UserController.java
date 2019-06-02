package com.lyh.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import com.lyh.util.CryptographyUtil;
import com.lyh.util.ResponseUtil;
import com.lyh.bean.Result;
import com.lyh.bean.User;
import com.lyh.service.UserServiceInf;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	UserServiceInf userServiceInf;
	//注册
	@RequestMapping("/add")
	public String add(User user, HttpServletResponse response, HttpServletRequest request) throws Exception {
		user.setPassword(CryptographyUtil.md5(user.getPassword(), "lyh"));
		user.setCreateDateTime(new Date());
		user.setMenuIds("4,5,40001,40002,40003,50001,50002");
		int resultTotal = userServiceInf.add(user);
		Result result = new Result();
		Gson gson = new Gson();
		if (resultTotal > 0) {
			result.setSuccess(true);
			result.setMsg("添加成功");
		} else {
			result.setSuccess(false);
			result.setMsg("添加失败");
		}
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
	//登录
	@RequestMapping("/login")
	public String login(User user,HttpServletResponse response,HttpServletRequest request
			,RedirectAttributes attr)throws Exception{
		Result result = new  Result();
		Gson gson = new Gson();
		Subject subject=SecurityUtils.getSubject();
		
		SecurityUtils.getSubject().getSession().setAttribute("login_type", "user_login");
		
		UsernamePasswordToken token=new UsernamePasswordToken(user.getNum_(), CryptographyUtil.md5(user.getPassword(), "lyh"));
		
		try{
			subject.login(token); // 登录验证
			//如果登陆成功 就不会报错  报错就是登陆失败了
			user = userServiceInf.findByNum(user.getNum_());
			SecurityUtils.getSubject().getSession().setAttribute("currentUser", user); //把当前用户信息存到session中
			
			result.setSuccess(true);
			result.setMsg("登录成功");
			ResponseUtil.write(response, gson.toJson(result));
			return null;
		}catch(Exception e){
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("帐号或密码错误");
			ResponseUtil.write(response, gson.toJson(result));
			return null;
		}
	}
	
	/**
	 * /user/logout
	 * @throws Exception
	 */
	@RequestMapping("/logout")
	public String logout()throws Exception{
		SecurityUtils.getSubject().logout(); //shiro的退出
		return "redirect:/login";
	}
}
