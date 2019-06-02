package com.lyh.mapper;

import java.util.List;
import java.util.Map;

import com.lyh.bean.Message;

public interface MessageMapper {

	Integer add(Message msg);

	List<Message> list(Map<String, Object> map);

	Integer getTotal(Map<String, Object> map);

	Integer delete(Integer id);

}
