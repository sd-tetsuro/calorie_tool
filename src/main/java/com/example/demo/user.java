package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer")
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
	public user() {

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


}