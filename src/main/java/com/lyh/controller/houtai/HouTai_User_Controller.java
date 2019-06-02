package com.lyh.controller.houtai;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lyh.bean.User;
import com.lyh.service.UserServiceInf;

@Controller
@RequestMapping("houtai/user")
public class HouTai_User_Controller {
	@Autowired
	UserServiceInf userServiceInf;
	
	@RequestMapping("manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "用户信息管理和维护");
		mav.addObject("title", "用户信息管理和维护");
		mav.setViewName("admin/page/user/user_manage");
		return mav;
	}
	
	@RequestMapping("edit")
	public ModelAndView edit(@RequestParam(value="id",required=false)String id
			,HttpServletResponse response
			,HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		User user = userServiceInf.findById(Integer.parseInt(id));
		
		mav.addObject("user", user);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "admin/user/update?id="+id);
		
		mav.setViewName("admin/page/user/add_or_update");
		return mav;
	}
	
	@RequestMapping("my")
	public ModelAndView my() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "修改资料");
		mav.addObject("title", "修改资料");
		mav.setViewName("admin/page/user/my_user_manage");
		return mav;
	}
	
	@RequestMapping("setPassword")
	public ModelAndView setPassword(@RequestParam(value = "isUser", required = false) String isUser,
			HttpServletResponse response, HttpServletRequest request) throws Exception {
		//如果id有值就是 更新 如果没有值  就是添加
		ModelAndView mav = new ModelAndView();
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		mav.addObject("pageTitle", "修改密码");
		mav.addObject("save_url", "admin/user/update?id="+user.getId());
		mav.setViewName("admin/page/user/set_password");
		return mav;
	}
	
	@RequestMapping("editmessage")
	public ModelAndView editmessage(@RequestParam(value = "isUser", required = false) String isUser
			,HttpServletResponse response
			,HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		User user1 = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		Integer id=user1.getId();
		User user = userServiceInf.findById(id);
		
		mav.addObject("user", user);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "admin/user/update?id="+id);
		
		mav.setViewName("admin/page/user/add_or_update");
		return mav;
	}
}
