package com.lyh.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyh.bean.User;
import com.lyh.mapper.UserMapper;
@Service
public class UserServiceImp implements UserServiceInf{
	@Autowired
	UserMapper userMapper;
	//注册增加用户的方法
	@Override
	public int add(User user) {
		return userMapper.add(user);
	}
	//登录通过账号查找返回用户
	@Override
	public User findByNum(String num) {
		return userMapper.findByNum(num);
	}
	@Override
	public User findById(Integer id) {
		return userMapper.findById(id);
	}
	@Override
	public Integer update(User user) {
		// TODO Auto-generated method stub
		return userMapper.update(user);
	}
	@Override
	public List<User> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userMapper.list(map);
	}
	@Override
	public Integer getTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userMapper.getTotal(map);
	}
	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.delete(id);
	}

}
