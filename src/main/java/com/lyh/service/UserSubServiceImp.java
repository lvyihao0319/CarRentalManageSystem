package com.lyh.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyh.bean.UserSub;
import com.lyh.mapper.UserSubMapper;
@Service
public class UserSubServiceImp implements UserSubServiceInf{
	@Autowired
	UserSubMapper userSubMapper;

	@Override
	public Integer add(UserSub userSub) {
		// TODO Auto-generated method stub
		return userSubMapper.add(userSub);
	}

	@Override
	public List<UserSub> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userSubMapper.list(map);
	}

	@Override
	public Integer getTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userSubMapper.getTotal(map);
	}

	@Override
	public UserSub findById(Integer id) {
		// TODO Auto-generated method stub
		return userSubMapper.findById(id);
	}

	@Override
	public Integer update(UserSub userSub) {
		// TODO Auto-generated method stub
		return userSubMapper.update(userSub);
	}

}
