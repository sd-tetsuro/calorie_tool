package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mylists")
public class mylists {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer code;
	private Integer categorycode;
	private String dishname;
	private String uname;
	private Integer kcalall;
	private Integer grams;
	private Integer kcal;


	public mylists(String dishname, String uname, Integer kcalall, Integer grams, Integer kcal) {

		this.dishname = dishname;
		this.uname = uname;
		this.kcalall = kcalall;
		this.grams = grams;
		this.kcal = kcal;
	}


	public mylists(String uname, Integer grams, Integer kcal) {
		super();
		this.uname = uname;
		this.grams = grams;
		this.kcal = kcal;
	}

	public mylists(String dishname, Integer kcal) {
		this.dishname = dishname;
		this.kcal = kcal;
	}



	public mylists(Integer code, Integer categorycode, String dishname, String uname, Integer kcalall, Integer grams,
			Integer kcal) {
		super();
		this.code = code;
		this.categorycode = categorycode;
		this.dishname = dishname;
		this.uname = uname;
		this.kcalall = kcalall;
		this.grams = grams;
		this.kcal = kcal;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Integer getCategorycode() {
		return categorycode;
	}
	public void setCategorycode(Integer categorycode) {
		this.categorycode = categorycode;
	}
	public String getDishname() {
		return dishname;
	}
	public void setDishname(String dishname) {
		this.dishname = dishname;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public Integer getKcalall() {
		return kcalall;
	}
	public void setKcalall(Integer kcalall) {
		this.kcalall = kcalall;
	}
	public Integer getGrams() {
		return grams;
	}
	public void setGrams(Integer grams) {
		this.grams = grams;
	}
	public Integer getKcal() {
		return kcal;
	}
	public void setKcal(Integer kcal) {
		this.kcal = kcal;
	}


}
