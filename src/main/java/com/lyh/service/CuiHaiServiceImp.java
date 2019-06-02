package com.lyh.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyh.bean.CuiHai;
import com.lyh.mapper.CuiHaiMapper;
@Service
public class CuiHaiServiceImp implements CuiHaiServiceInf{
	@Autowired
	CuiHaiMapper cuiHaiMapper;

	@Override
	public Integer deleteBySubId(Integer subId) {
		// TODO Auto-generated method stub
		return cuiHaiMapper.deleteBySubId(subId);
	}

	@Override
	public CuiHai findBySubId(Integer subId) {
		// TODO Auto-generated method stub
		return cuiHaiMapper.findBySubId(subId);
	}

	@Override
	public Integer add(CuiHai cuihai) {
		// TODO Auto-generated method stub
		return cuiHaiMapper.add(cuihai);
	}

	@Override
	public List<CuiHai> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return cuiHaiMapper.list(map);
	}

	@Override
	public Integer getTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return cuiHaiMapper.getTotal(map);
	}

}
