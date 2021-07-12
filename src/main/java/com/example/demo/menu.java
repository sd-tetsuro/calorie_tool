package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="menu")
public class menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

private Integer code;
private String dishname;
private Integer dishcode;

public menu() {
	super();
}

public menu(String dishname, Integer dishcode) {
	this.dishname = dishname;
	this.dishcode = dishcode;
}

public menu(Integer code, String dishname, Integer dishcode) {
	super();
	this.code = code;
	this.dishname = dishname;
	this.dishcode = dishcode;
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
