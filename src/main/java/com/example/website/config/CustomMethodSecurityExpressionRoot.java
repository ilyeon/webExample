package com.example.website.config;

import com.example.website.mapper.BoardMapper;
import com.example.website.mapper.CommentMapper;
import com.example.website.model.Board;
import com.example.website.model.Comment;
import com.example.website.model.LoginUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations
{
	private Object filterObject;
	private Object returnObject;    

	BoardMapper boardMapper;
	CommentMapper commentMapper;

	public CustomMethodSecurityExpressionRoot(Authentication authentication)
	{
		super(authentication);
	}

	public boolean isMyBoard(int id)
	{
		LoginUserDetails userDetails = (LoginUserDetails) this.getPrincipal();
		Board board = boardMapper.selectBoard(id);
		
		return board.getuserId() == userDetails.getId();
	}

	public boolean isMyComment(int id)
	{
		LoginUserDetails userDetails = (LoginUserDetails) this.getPrincipal();
		Comment comment = commentMapper.selectComment(id);

		return comment.getUserId() == userDetails.getId();
	}

	@Override
	public void setFilterObject(Object filterObject) {
		this.filterObject = filterObject;
	}

	@Override
	public Object getFilterObject() {
		return this.filterObject;
	}

	@Override
	public void setReturnObject(Object returnObject) {
	   this.returnObject = returnObject;
	}

	@Override
	public Object getReturnObject() {
	   return this.returnObject;
	}

	public BoardMapper getBoardMapper() {
		return this.boardMapper;
	}

	public void setBoardMapper(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

	public CommentMapper getCommentMapper() {
		return this.commentMapper;
	}

	public void setCommentMapper(CommentMapper commentMapper) {
		this.commentMapper = commentMapper;
	}

	@Override
	public Object getThis() {
		return this;
	}
}