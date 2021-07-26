package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class user {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="code")
	private Integer code;

	@Column(name="name")
	private String name;

	@Column(name="email")
	private String email;

	@Column(name="password")
	private String password;

	private Integer weight;

	private Integer height;

	private Integer age;

	private Integer gender;

	public user()
	{}


	public user(String name, Integer weight, Integer height, Integer age, Integer gender) {
		super();
		this.name = name;
		this.weight = weight;
		this.height = height;
		this.age = age;
		this.gender = gender;
	}


	public user(Integer code, String name, Integer weight, Integer height, Integer age, Integer gender) {
		super();
		this.code = code;
		this.name = name;
		this.weight = weight;
		this.height = height;
		this.age = age;
		this.gender = gender;
	}


	public user(Integer code, String name,  String email,String password) {
		this.code = code;
		this.name = name;
		this.email = email;
		this.password = password;

	}

	public user(String name, String email,  String password) {

		this.name = name;
		this.email = email;
		this.password = password;
	}



	public user(Integer code, String name, String email, String password, Integer weight, Integer height, Integer age) {
		super();
		this.code = code;
		this.name = name;
		this.email = email;
		this.password = password;
		this.weight = weight;
		this.height = height;
		this.age = age;
	}

	public user(Integer code, String name, String email, String password, Integer weight, Integer height, Integer age,
			Integer gender) {
		super();
		this.code = code;
		this.name = name;
		this.email = email;
		this.password = password;
		this.weight = weight;
		this.height = height;
		this.age = age;
		this.gender = gender;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}



}