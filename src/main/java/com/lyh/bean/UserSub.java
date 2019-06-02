package com.lyh.bean;

import java.util.Date;

/**
 * 用户租用信息表单
 * @author Administrator
 * 主要字段有：账号、姓名、租用车辆编号、租用日期、还车日期
 * 要求：租车时，需自动查阅该车库存数量，借出后，将该车库存数量自动减少 
 */
public class UserSub {
	
	private Integer id;
	private Integer userId;
	private Integer carId;
	private Date subDateTime;//租用时间 
	private Date returnDateTime;//归还 时间 
	private Integer type;//1未还 2已还
	
	
	
	private User user;
	private Car car;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCarId() {
		return carId;
	}
	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	public Date getSubDateTime() {
		return subDateTime;
	}
	public void setSubDateTime(Date subDateTime) {
		this.subDateTime = subDateTime;
	}
	public Date getReturnDateTime() {
		return returnDateTime;
	}
	public void setReturnDateTime(Date returnDateTime) {
		this.returnDateTime = returnDateTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	
	
}
