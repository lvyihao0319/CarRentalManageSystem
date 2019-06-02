package com.lyh.bean;

import java.util.Date;

/**
 * 车辆催还信息表单
 * @author Administrator
 * 账号、姓名、品牌、价格、租用日期（由车辆库存信息表单和用户租用信息表单自动生成车辆催还信息表单，凡租用时间超过2个月则催还。） 
 * 
 */
public class CuiHai {
	
	private Integer id;
	private Integer userSubId;//租用记录id
	private Integer jierenId;//租用人id
	private Integer carId;//租用车辆id
	private Date createDateTime;//催还创建时间 
	
	
	private UserSub userSub;//租用记录
	private User jieren;//租用人
	private Car car;//租的车
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserSubId() {
		return userSubId;
	}
	public void setUserSubId(Integer userSubId) {
		this.userSubId = userSubId;
	}
	public Integer getJierenId() {
		return jierenId;
	}
	public void setJierenId(Integer jierenId) {
		this.jierenId = jierenId;
	}
	public Integer getCarId() {
		return carId;
	}
	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public UserSub getUserSub() {
		return userSub;
	}
	public void setUserSub(UserSub userSub) {
		this.userSub = userSub;
	}
	public User getJieren() {
		return jieren;
	}
	public void setJieren(User jieyueren) {
		this.jieren = jieyueren;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	
	
	
}
