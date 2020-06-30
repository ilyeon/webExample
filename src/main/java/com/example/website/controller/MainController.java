package com.example.website.controller;

import com.example.website.model.User;
import com.example.website.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String createUser(User user, String passwordCheck, Model model)
    {
        if(user.getName() == null || user.getName() == "")
        {
            model.addAttribute("nameBlank", "이름 미입력");
            return "register";
        }

        if(user.getLogin() == null)
        {
            model.addAttribute("idBlank", "아이디 미입력");
            return "register";
        }

        if(user.getPassword() == null)
        {
            model.addAttribute("pwdBlank", "비밀번호 미입력");
            return "register";
        }

        if(!user.getPassword().equals(passwordCheck))
        {
            model.addAttribute("errorPwdCheck", "비밀번호 불일치");
            return "register";
        }
        
        try
        {
            userService.createUser(user);
            return "redirect:/login";
        } 
        catch (final Exception e)
        {
            model.addAttribute("idDuplicate", "아이디 중복");
            return "register";
        }
    }
}