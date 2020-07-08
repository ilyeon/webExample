package com.example.website.service;

import com.example.website.exception.DuplicateLoginIdException;
import com.example.website.mapper.UserMapper;
import com.example.website.model.LoginUserDetails;
import com.example.website.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService
{
	@Autowired
	UserMapper userMapper;

	@Autowired
	PasswordEncoder passwordEncoder;

	public void createUser(User user)
	{
		//아이디 중복 체크
		if(userMapper.selectUserByUsername(user.getLogin()) == null)
		{
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userMapper.insertUser(user);
		}
		else
		{
			throw new DuplicateLoginIdException();
		}
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException
	{
		User user = userMapper.selectUserByUsername(login);
		
		if (user == null)
		{
			// 계정이 존재하지 않음
			throw new UsernameNotFoundException("login fail");
		}
		
		return new LoginUserDetails(user);
	}
}