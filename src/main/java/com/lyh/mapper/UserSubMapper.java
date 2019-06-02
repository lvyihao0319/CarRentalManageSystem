package com.lyh.mapper;

import java.util.List;
import java.util.Map;

import com.lyh.bean.UserSub;

public interface UserSubMapper {

	Integer add(UserSub userSub);

	List<UserSub> list(Map<String, Object> map);

	Integer getTotal(Map<String, Object> map);

	UserSub findById(Integer id);

	Integer update(UserSub userSub);

}
