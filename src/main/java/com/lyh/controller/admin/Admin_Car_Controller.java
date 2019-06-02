package com.lyh.controller.admin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.lyh.bean.Car;
import com.lyh.bean.PageBean;
import com.lyh.bean.Result;
import com.lyh.service.CarServiceInf;
import com.lyh.util.ResponseUtil;
import com.lyh.util.StringUtil;
import com.lyh.util.MyFileUpload;



@Controller
@RequestMapping("admin/car")
public class Admin_Car_Controller {
	
	@Autowired
	CarServiceInf carServiceInf;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
    }
	
	@RequestMapping(value = "upload/image" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object>  upload(HttpServletRequest servletRequest,
                         @RequestParam("file") MultipartFile[] file
                         ) throws IOException {

		Result result = new Result();
		//如果文件内容不为空，则写入上传路径
        if (file.length!=0) {
        	List<String> list_image=MyFileUpload.upload_image(file);
        	Map<String, Object> res = new HashMap<>();
            //返回的是一个url对象
            res.put("url", list_image);
            return res;

        } else {
        	result.setMsg("添加失败");
        }
        return null;
    }

	
	/**
	 * /admin/car/add
	 */
	@RequestMapping("add")
	public String add(Car car, HttpServletResponse response, HttpServletRequest request) throws Exception {
		int resultTotal = carServiceInf.add(car);
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
	/**
	 * /admin/car/list
	 * @param page
	 * @param rows
	 * @param q
	 */
	@RequestMapping("list")
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
		List<Car> list = carServiceInf.list(map);
		Integer total = carServiceInf.getTotal(map);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		map.clear();
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		ResponseUtil.write(response, gson.toJson(map));
		return null;
	}
	
	/**
	 * /admin/car/update
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("update")
	public String update(Car car, HttpServletResponse response, HttpServletRequest request) throws Exception {
		int resultTotal = carServiceInf.update(car);
		Result result = new Result();
		Gson gson = new Gson();
		if (resultTotal > 0) {
			result.setSuccess(true);
			result.setMsg("修改成功");
		} else {
			result.setSuccess(false);
			result.setMsg("修改失败");
		}
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
	
	/**
	 * /admin/car/delete
	 */
	@RequestMapping("delete")
	public String delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
			throws Exception {
		String[] idsStr = ids.split(",");
		Gson gson = new Gson();
		Result result = new Result();
		for (int i = 0; i < idsStr.length; i++) {
			carServiceInf.delete(Integer.parseInt(idsStr[i]));
		}
		result.setSuccess(true);
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
}
