package com.lyh.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lyh.service.PublicServiceInf;


@Controller
public class IndexController {
	@Autowired
	PublicServiceInf publicServiceinf;

	/**
	 * 登录页面
	 */
	@RequestMapping("login")
	public String login() {
		return "login/login";
	}
	/**
	 * 后台主页
	 */
	@RequestMapping("admin/main")
	public ModelAndView admin_main(HttpServletResponse  res,HttpServletRequest req) throws Exception {
		ModelAndView mav = new ModelAndView();
		publicServiceinf.addLeftMenu(mav);
		mav.setViewName("admin/main");
		return mav;
	}
	
	
}
