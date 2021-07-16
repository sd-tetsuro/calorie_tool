package com.example.demo;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="kcal")
public class Kcal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer code;
	private Integer usercode ;
	private Date date;
	private Time  time;
	private String dishname;
	private Integer kcalall;

	public Kcal() {

	}

public Kcal(Integer code, Integer usercode, Date date, Time  time, String dishname, Integer kcalall) {
		super();
		this.code = code;
		this.usercode = usercode;
		this.date = date;
		this.time = time;
		this.dishname = dishname;
		this.kcalall = kcalall;
	}

public Kcal( Date date) {
	super();
	this.date = date;
}
public Kcal(  Date date, Time  time, String dishname, Integer kcalall, Integer usercode) {
	super();
	this.date = date;
	this.time = time;
	this.dishname = dishname;
	this.kcalall = kcalall;
	this.usercode = usercode;

}
public Integer getCode() {
	return code;
}

public void setCode(Integer code) {
	this.code = code;
}

public Integer getUsercode() {
	return usercode;
}

public void setUsercode(Integer usercode) {
	this.usercode = usercode;
}

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}

public Time  getTime() {
	return time;
}

public void setTime(Time  time) {
	this.time = time;
}

public String getDishname() {
	return dishname;
}

public void setDishname(String dishname) {
	this.dishname = dishname;
}

public Integer getKcalall() {
	return kcalall;
}

public void setKcalall(Integer kcalall) {
	this.kcalall = kcalall;
}
}
