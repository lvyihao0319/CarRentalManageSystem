package com.lyh.controller.houtai;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("houtai/car/sub")
public class HouTai_Car_Sub_Controller {
	/**
	 * /houtai/car/sub/manage
	 */
	@RequestMapping("manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/page/car_sub/car_sub_manage");
		return mav;
	}
}
