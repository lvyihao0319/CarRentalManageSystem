package com.lyh.controller.houtai;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("houtai/message")
public class HouTai_Message_Controller {
	/**
	 * /houtai/message/my
	 */
	@RequestMapping("my")
	public ModelAndView my() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/page/message/my_message_manage");
		return mav;
	}
	
	/**
	 * /houtai/message/add
	 */
	@RequestMapping("add")
	public ModelAndView add() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "admin/message/add");
		mav.setViewName("admin/page/message/add_or_update");
		return mav;
	}
	
	/**
	 * /houtai/message/manage
	 */
	@RequestMapping("manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/page/message/message_manage");
		return mav;
	}
}
