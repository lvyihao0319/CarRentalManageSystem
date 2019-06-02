package com.lyh.service;

import java.util.List;
import java.util.Map;

import com.lyh.bean.Tree;

public interface TreeServiceInf {

	List<Tree> getTreesByFatherOrIds(Map<String, Object> map);

}
