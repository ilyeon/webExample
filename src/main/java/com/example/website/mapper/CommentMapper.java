package com.example.website.mapper;

import com.example.website.model.Comment;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper
{
    public void insertComment(Comment comment);
}