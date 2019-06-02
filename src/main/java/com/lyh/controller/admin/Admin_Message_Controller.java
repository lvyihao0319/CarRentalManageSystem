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

import com.lyh.bean.Message;
import com.lyh.bean.PageBean;
import com.lyh.bean.Result;
import com.lyh.bean.User;
import com.lyh.service.MessageServiceInf;
import com.lyh.util.ResponseUtil;
import com.lyh.util.StringUtil;

@Controller
@RequestMapping("admin/message")
public class Admin_Message_Controller {
	
	@Autowired
	MessageServiceInf messageServiceInf;
	/**
	 * /admin/message/add
	 */
	@RequestMapping("add")
	public String add(Message msg  , HttpServletResponse response, HttpServletRequest request) throws Exception {
		User currentUser =	(User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		msg.setUserId(currentUser.getId());
		msg.setCreateDateTime(new Date());
		
		int resultTotal = messageServiceInf.add(msg);
		Result result = new Result();
		Gson gson = new Gson();
		if (resultTotal > 0) {
			result.setSuccess(true);
			result.setMsg("留言成功");
		} else {
			result.setSuccess(false);
			result.setMsg("留言失败");
		}
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
	
	/**
	 * /admin/message/list
	 * @param page
	 * @param rows
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "limit", required = false) String rows,
			@RequestParam(value = "q", required = false) String q, 
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("q", StringUtil.formatLike(q));
		List<Message> list = messageServiceInf.list(map);
		Integer total = messageServiceInf.getTotal(map);
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
	 * /admin/message/delete
	 */
	@RequestMapping("delete")
	public String delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
			throws Exception {
		String[] idsStr = ids.split(",");
		Gson gson = new Gson();
		Result result = new Result();
		for (int i = 0; i < idsStr.length; i++) {
			messageServiceInf.delete(Integer.parseInt(idsStr[i]));
		}
		result.setSuccess(true);
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
}
