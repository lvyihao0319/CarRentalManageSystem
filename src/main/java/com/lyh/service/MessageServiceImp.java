package com.lyh.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyh.bean.Message;
import com.lyh.mapper.MessageMapper;
@Service
public class MessageServiceImp implements MessageServiceInf{
	@Autowired
	MessageMapper messageMapper;
	
	@Override
	public Integer add(Message msg) {
		// TODO Auto-generated method stub
		return messageMapper.add(msg);
	}

	@Override
	public List<Message> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return messageMapper.list(map);
	}

	@Override
	public Integer getTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return messageMapper.getTotal(map);
	}

	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return messageMapper.delete(id);
	}

}
