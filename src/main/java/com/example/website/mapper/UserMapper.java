package com.example.website.mapper;

import com.example.website.model.User;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper
{
    public void insertUser(User user);
    public User selectUserByUsername(String login);
}