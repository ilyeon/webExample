package com.example.website.controller;

import com.example.website.exception.DuplicateLoginIdException;
import com.example.website.model.LoginUserDetails;
import com.example.website.model.User;
import com.example.website.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller 
public class MainController
{
	@Autowired
	UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String base(Authentication authentication)
	{
	   return "redirect:/main";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, Authentication authentication)
	{
		if (authentication == null)
		{
			return "login";
		}
		else
		{
			return "redirect:/main";
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register()
	{
		return "register";
	}

	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String createUser(User user, String passwordCheck, Model model)
	{
		try
		{
			userService.createUser(user);
		} 
		catch (DuplicateLoginIdException e)
		{
			return "fail";
		}

		return "success";
	}
}