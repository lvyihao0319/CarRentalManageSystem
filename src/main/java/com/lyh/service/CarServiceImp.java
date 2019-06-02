package com.lyh.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyh.bean.Car;
import com.lyh.mapper.CarMapper;
@Service
public class CarServiceImp implements CarServiceInf{
	@Autowired
	CarMapper carMapper;

	@Override
	public int add(Car car) {
		// TODO Auto-generated method stub
		return carMapper.add(car);
	}

	@Override
	public List<Car> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return carMapper.list(map);
	}

	@Override
	public Integer getTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return carMapper.getTotal(map);
	}

	@Override
	public Car findById(Integer id) {
		// TODO Auto-generated method stub
		return carMapper.findById(id);
	}

	@Override
	public int update(Car car) {
		// TODO Auto-generated method stub
		return carMapper.update(car);
	}

	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return carMapper.delete(id);
	}

	@Override
	public Car findAll() {
		// TODO Auto-generated method stub
		return carMapper.findAll();
	}
}
