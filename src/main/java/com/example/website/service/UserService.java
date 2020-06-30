package com.example.website.service;

import com.example.website.mapper.UserMapper;
import com.example.website.model.LoginUserDetails;
import com.example.website.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService
{
	@Autowired
	UserMapper userMapper;

	@Autowired
	PasswordEncoder passwordEncoder;

	public boolean createUser(User user)
	{
		User alreadyUser = userMapper.selectUserByUsername(user.getLogin());

		if(alreadyUser == null)
		{
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userMapper.insertUser(user);
			return true;
		}
		else
		{
			return false;
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