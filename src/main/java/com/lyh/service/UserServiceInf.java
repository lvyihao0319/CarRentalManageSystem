package com.lyh.service;

import java.util.List;
import java.util.Map;

import com.lyh.bean.User;

public interface UserServiceInf {

	int add(User user);

	User findByNum(String num);

	User findById(Integer id);

	public Integer update(User user);

	List<User> list(Map<String, Object> map);

	Integer getTotal(Map<String, Object> map);

	Integer delete(Integer id);

}
