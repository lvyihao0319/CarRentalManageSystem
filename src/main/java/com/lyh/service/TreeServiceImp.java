package com.lyh.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyh.bean.Tree;
import com.lyh.mapper.TreeMapper;
@Service
public class TreeServiceImp implements TreeServiceInf{
	@Autowired
	TreeMapper treeMapper;

	@Override
	public List<Tree> getTreesByFatherOrIds(Map<String, Object> map) {
		return treeMapper.getTreesByFatherOrIds(map);
	}

}
