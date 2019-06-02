package com.lyh.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.lyh.bean.Car;
import com.lyh.bean.PageBean;
import com.lyh.bean.Result;
import com.lyh.bean.User;
import com.lyh.bean.UserSub;
import com.lyh.service.CarServiceInf;
import com.lyh.service.CuiHaiServiceInf;
import com.lyh.service.UserSubServiceInf;
import com.lyh.util.ResponseUtil;


@Controller
@RequestMapping("admin/user/sub")
public class Admin_User_Sub_Controller {
	@Autowired
	CarServiceInf carServiceInf;
	@Autowired
	UserSubServiceInf userSubServIceInf;
	@Autowired
	CuiHaiServiceInf cuiHaiServiceInf;
	
	/**
	 * /admin/user/sub/add
	 */
	@RequestMapping("add")
	public String add(@RequestParam(value = "carId", required = false) String carId, 
			HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		Car car = carServiceInf.findById(Integer.parseInt(carId));
		Result result = new Result();
		Gson gson = new Gson();
		
		if(car.getKucun()==0){
			result.setSuccess(false);
			result.setMsg("此车库存不足,无法租用");
			ResponseUtil.write(response, gson.toJson(result));
			return null;
		}
		
		User currentUser =	(User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		UserSub sub = new UserSub();
		sub.setCarId(car.getId());
		sub.setSubDateTime(new Date());
		sub.setUserId(currentUser.getId());
		sub.setType(1);
		
		int resultTotal = userSubServIceInf.add(sub);
		
		if (resultTotal > 0) {
			car.setKucun(car.getKucun()-1);
			carServiceInf.update(car);
			
			result.setSuccess(true);
			result.setMsg("租用成功");
		} else {
			result.setSuccess(false);
			result.setMsg("租用失败");
		}
		
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
	
	/**
	 * /admin/user/sub/list
	 */
	@RequestMapping("list")
	public String list(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "limit", required = false) String rows,
			@RequestParam(value = "date1", required = false) String date1, 
			@RequestParam(value = "date2", required = false) String date2, 
			@RequestParam(value = "userId", required = false) String userId, 
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("date1", date1);
		map.put("date2", date2);
		map.put("userId", userId);
		
		List<UserSub> list = userSubServIceInf.list(map);
		Integer total = userSubServIceInf.getTotal(map);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		
		map.clear();
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		ResponseUtil.write(response, gson.toJson(map));
		return null;
	}
	/**
	 * /admin/user/sub/return_
	 */
	@RequestMapping("return_")
	public String return_(@RequestParam(value = "subId", required = false) String subId, HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		Result result = new Result();
		Gson gson = new Gson();
		
		UserSub sub = userSubServIceInf.findById(Integer.parseInt(subId));
		if(sub.getReturnDateTime()!=null){
			result.setSuccess(false);
			result.setMsg("已还车，请勿重复操作");
			ResponseUtil.write(response, gson.toJson(result));
			return null;
		}
		
		sub.setReturnDateTime(new Date());
		sub.setType(2);
		int resultTotal = userSubServIceInf.update(sub);
		
		if (resultTotal > 0) {
			Car car = carServiceInf.findById(sub.getCarId());
			car.setKucun(car.getKucun()+1);
			carServiceInf.update(car);
			
			//删除催还记录
			cuiHaiServiceInf.deleteBySubId(Integer.parseInt(subId));
			
			result.setSuccess(true);
			result.setMsg("还车成功");
		} else {
			result.setSuccess(false);
			result.setMsg("还车失败");
		}
		
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
}
