package com.lyh.service;

import java.util.List;
import java.util.Map;

import com.lyh.bean.Message;

public interface MessageServiceInf {

	Integer add(Message  msg);

	List<Message> list(Map<String, Object> map);

	Integer getTotal(Map<String, Object> map);

	Integer delete(Integer id);

}
