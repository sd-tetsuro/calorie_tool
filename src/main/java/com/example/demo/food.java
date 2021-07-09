package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="food")
public class food {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer code;
	private Integer categorycode;
	private String name;
	private String uname;
	private Integer kcal;
	private Integer grams;

	public food() {

	}


	public food(Integer categorycode) {
		super();
		this.categorycode = categorycode;
	}

	public food(Integer code, Integer categorycode, String name, String uname, Integer kcal, Integer grams) {
		super();
		this.code = code;
		this.categorycode = categorycode;
		this.name = name;
		this.uname = uname;
		this.kcal = kcal;
		this.grams = grams;
	}

	public food(Integer code, Integer categorycode, String name, String uname, Integer kcal) {
		super();
		this.code = code;
		this.categorycode = categorycode;
		this.name = name;
		this.uname = uname;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public Integer getKcal() {
		return kcal;
	}

	public void setKcal(Integer kcal) {
		this.kcal = kcal;
	}

	public Integer getGrams() {
		return grams;
	}

	public void setGrams(Integer grams) {
		this.grams = grams;
	}

}
