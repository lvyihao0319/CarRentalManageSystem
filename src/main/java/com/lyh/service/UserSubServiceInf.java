package com.lyh.service;

import java.util.List;
import java.util.Map;

import com.lyh.bean.UserSub;

public interface UserSubServiceInf {

	Integer add(UserSub userSub);

	List<UserSub> list(Map<String, Object> map);

	Integer getTotal(Map<String, Object> map);

	UserSub findById(Integer id);

	Integer update(UserSub userSub);

}
