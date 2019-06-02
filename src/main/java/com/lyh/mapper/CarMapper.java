package com.lyh.mapper;

import java.util.List;
import java.util.Map;

import com.lyh.bean.Car;

public interface CarMapper {

	int add(Car car);

	List<Car> list(Map<String, Object> map);

	Integer getTotal(Map<String, Object> map);

	Car findById(Integer id);

	int update(Car car);

	Integer delete(Integer id);

	Car findAll();

}
