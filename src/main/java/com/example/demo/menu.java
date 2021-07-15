package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menu")
public class menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer code;
	private String dishname;
	private Integer dishcode;
	private Integer kcalall;
	private Integer userid;

	public menu() {
		super();
	}

	public menu(Integer code, String dishname, Integer dishcode, Integer kcalall, Integer userid) {
		super();
		this.code = code;
		this.dishname = dishname;
		this.dishcode = dishcode;
		this.kcalall = kcalall;
		this.userid = userid;
	}

	public menu(String dishname, Integer dishcode, Integer kcalall, Integer userid) {
		super();
		this.dishname = dishname;
		this.dishcode = dishcode;
		this.kcalall = kcalall;
		this.userid = userid;
	}

	public menu(String dishname, Integer kcalall) {
		super();
		this.dishname = dishname;
		this.kcalall = kcalall;
	}

	public menu(Integer code, String dishname, Integer dishcode, Integer kcalall) {
		super();
		this.code = code;
		this.dishname = dishname;
		this.dishcode = dishcode;
		this.kcalall = kcalall;
	}

	public menu(String dishname, Integer dishcode, Integer kcalall) {
		super();
		this.dishname = dishname;
		this.dishcode = dishcode;
		this.kcalall = kcalall;
	}

	public menu(Integer code, String dishname, Integer dishcode) {
		super();
		this.code = code;
		this.dishname = dishname;
		this.dishcode = dishcode;
	}

	public Integer getKcalall() {
		return kcalall;
	}

	public void setKcalall(Integer kcalall) {
		this.kcalall = kcalall;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDishname() {
		return dishname;
	}

	public void setDishname(String dishname) {
		this.dishname = dishname;
	}

	public Integer getDishcode() {
		return dishcode;
	}

	public void setDishcode(Integer dishcode) {
		this.dishcode = dishcode;
	}

}
