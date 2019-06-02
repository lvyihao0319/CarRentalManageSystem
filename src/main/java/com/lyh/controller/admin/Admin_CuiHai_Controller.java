package com.lyh.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.lyh.bean.CuiHai;
import com.lyh.bean.PageBean;
import com.lyh.bean.Result;
import com.lyh.bean.UserSub;
import com.lyh.service.CuiHaiServiceInf;
import com.lyh.service.UserSubServiceInf;
import com.lyh.util.ResponseUtil;


@Controller
@RequestMapping("admin/cuihai")
public class Admin_CuiHai_Controller {
	@Autowired
	UserSubServiceInf userSubServiceInf;
	@Autowired
	CuiHaiServiceInf cuiHaiServiceInf;
	/**
	 *   /admin/cuihai/add
	 */
	@RequestMapping("add")
	public String add(@RequestParam(value = "userSubId", required = false) String userSubId, HttpServletResponse response, HttpServletRequest request) throws Exception {
		UserSub userSub = userSubServiceInf.findById(Integer.parseInt(userSubId));
		
		CuiHai cuiHai = cuiHaiServiceInf.findBySubId(Integer.parseInt(userSubId));
		
		Result result = new Result();
		Gson gson = new Gson();
		
		if(cuiHai!=null){
			result.setSuccess(false);
			result.setMsg("已在催还列表");
			ResponseUtil.write(response, gson.toJson(result));
			return null;
		}
		
		cuiHai = new CuiHai();
		cuiHai.setCreateDateTime(new Date());
		cuiHai.setCarId(userSub.getCarId());
		cuiHai.setUserSubId(userSub.getId());
		cuiHai.setJierenId(userSub.getUserId());
		
		int resultTotal = cuiHaiServiceInf.add(cuiHai);
		
		if (resultTotal > 0) {
			result.setSuccess(true);
			result.setMsg("已添加到催还列表");
		} else {
			result.setSuccess(false);
			result.setMsg("催还失败");
		}
		
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
	
	/**
	 * /admin/cuihai/list
	 */
	@RequestMapping("list")
	public String list(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "limit", required = false) String rows,
			@RequestParam(value = "userId", required = false) String userId, 
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("jierenId", userId);
		
		List<CuiHai> list = cuiHaiServiceInf.list(map);
		Integer total = cuiHaiServiceInf.getTotal(map);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		
		map.clear();
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		ResponseUtil.write(response, gson.toJson(map));
		return null;
	}
}
