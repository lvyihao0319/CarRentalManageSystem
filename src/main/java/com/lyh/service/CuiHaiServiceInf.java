package com.lyh.service;

import java.util.List;
import java.util.Map;

import com.lyh.bean.CuiHai;

public interface CuiHaiServiceInf {

	Integer deleteBySubId(Integer subId);

	CuiHai findBySubId(Integer subId);

	Integer add(CuiHai cuihai);

	List<CuiHai> list(Map<String, Object> map);

	Integer getTotal(Map<String, Object> map);

}
