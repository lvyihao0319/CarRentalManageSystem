package com.lyh.mapper;

import java.util.List;
import java.util.Map;

import com.lyh.bean.CuiHai;

public interface CuiHaiMapper {

	Integer deleteBySubId(Integer subId);

	CuiHai findBySubId(Integer subId);

	Integer add(CuiHai cuihai);

	List<CuiHai> list(Map<String, Object> map);

	Integer getTotal(Map<String, Object> map);

}
