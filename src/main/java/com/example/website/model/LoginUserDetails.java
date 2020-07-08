package com.example.website.model;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class LoginUserDetails extends org.springframework.security.core.userdetails.User {
	private int id;
	private String login;
	private String name;

	public LoginUserDetails(User account) {
		super(account.getLogin(), account.getPassword(), authorities());
		id = account.getId();
		login = account.getLogin();
		name = account.getName();
	}

	private static List<SimpleGrantedAuthority> authorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
}