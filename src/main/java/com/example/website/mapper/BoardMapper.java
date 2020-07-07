package com.example.website.mapper;

import java.util.HashMap;
import java.util.List;

import com.example.website.model.Board;
import com.example.website.model.Criteria;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper
{
	public void insertBoard(Board board);
	public List<Board> selectBoardList(Criteria criteria);
	public int selectTotalCount(Criteria criteria);
	public Board selectBoard(int id);
	public Board selectBoardByCommentId(int commentId);
	public void updateBoard(Board board);
	public void deleteBoard(int id);
}