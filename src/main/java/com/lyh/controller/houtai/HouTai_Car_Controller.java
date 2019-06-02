package com.lyh.controller.houtai;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lyh.bean.Car;
import com.lyh.service.CarServiceInf;



@Controller
@RequestMapping("houtai/car")
public class HouTai_Car_Controller {
	@Autowired
	CarServiceInf carServiceInf;
	/**
	 * /houtai/car/manage
	 */
	@RequestMapping("manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
//		Car car=carServiceInf.findAll();
//		mav.addObject("car", car);
		mav.setViewName("admin/page/car/car_manage");
		return mav;
	}
	
	/**
	 * /houtai/car/add
	 */
	@RequestMapping("add")
	public ModelAndView add() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "admin/car/add");
		mav.setViewName("admin/page/car/add_or_update");
		return mav;
	}
	
	/**
	 * /houtai/car/edit?id=22
	 */
	@RequestMapping("edit")
	public ModelAndView edit(@RequestParam(value="id",required=false)String id
			,HttpServletResponse response
			,HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		Car car = carServiceInf.findById(Integer.parseInt(id));
		mav.addObject("car", car);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "admin/car/update?id="+id);
		mav.setViewName("admin/page/car/add_or_update");
		return mav;
	}
	
	@RequestMapping("look")
	public ModelAndView look(@RequestParam(value="id",required=false)String id
			,HttpServletResponse response
			,HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		Car car = carServiceInf.findById(Integer.parseInt(id));
		mav.addObject("car", car);
		mav.addObject("btn_text", "租用");
		
		mav.setViewName("admin/page/car_sub/look");
		return mav;
	}
}
