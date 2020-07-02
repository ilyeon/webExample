package com.example.website.mapper;

import java.util.List;

import com.example.website.model.Comment;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper
{
    public void insertComment(Comment comment);
    public List<Comment> selectCommentList(int boardId);
    public Comment selectComment(int id);
    public void updateComment(Comment comment);
    public void deleteComment(int id);
}