package com.lyh.controller.houtai;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lyh.bean.User;
import com.lyh.service.CuiHaiServiceInf;

@Controller
@RequestMapping("houtai/cuihai")
public class HouTai_CuiHai_Controller {
	@Autowired
	CuiHaiServiceInf cuiHaiServiceInf;
	
	/**
	 * /houtai/cuihai/manage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("manage")
	public ModelAndView manage(@RequestParam(value = "isUser", required = false) String isUser) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("table_url", "admin/cuihai/list");
		mav.setViewName("admin/page/cuihai/cuihai_manage");
		return mav;
	}
	
	/**
	 * /houtai/cuihai/my
	 * 我的催还记录
	 */
	@RequestMapping("my")
	public ModelAndView my(@RequestParam(value = "isUser", required = false) String isUser) throws Exception {
		ModelAndView mav = new ModelAndView();
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		mav.addObject("table_url", "admin/cuihai/list?userId="+user.getId());
		mav.setViewName("admin/page/cuihai/my_cuihai_manage");
		return mav;
	}
}
