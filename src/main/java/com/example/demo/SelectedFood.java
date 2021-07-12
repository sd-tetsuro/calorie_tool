package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="selectedfood")

public class SelectedFood {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer code;
	@Column(name="uname")
	private String uname;
	@Column(name="calresult")
	private Integer calResult;
	private Integer grams;
	@Column(name="dishcode")
    private Integer dishCode;
	public SelectedFood() {
		super();
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public SelectedFood(Integer code, String uname, Integer calResult, Integer grams, Integer dishCode) {
		super();
		this.code = code;
		this.uname = uname;
		this.calResult = calResult;
		this.grams = grams;
		this.dishCode = dishCode;
	}

	public SelectedFood(String uname, Integer calResult, Integer grams, Integer dishCode) {
		super();
		this.uname = uname;
		this.calResult = calResult;
		this.grams = grams;
		this.dishCode = dishCode;
	}

	public Integer getDishCode() {
		return dishCode;
	}

	public void setDishCode(Integer dishCode) {
		this.dishCode = dishCode;
	}

	public SelectedFood(String uname, Integer calResult, Integer grams) {
		super();
		this.uname = uname;
		this.calResult = calResult;
		this.grams = grams;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public Integer getCalResult() {
		return calResult;
	}
	public void setCalResult(Integer calResult) {
		this.calResult = calResult;
	}
	public Integer getGrams() {
		return grams;
	}
	public void setGrams(Integer grams) {
		this.grams = grams;
	}



}
