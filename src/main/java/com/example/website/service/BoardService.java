package com.example.website.service;

import java.util.HashMap;
import java.util.List;

import com.example.website.mapper.BoardMapper;
import com.example.website.mapper.CommentMapper;
import com.example.website.model.Board;
import com.example.website.model.Comment;
import com.example.website.model.Criteria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService
{
    @Autowired
    BoardMapper boardMapper;

    @Autowired
    CommentMapper commentMapper;

    public void createBoard(Board board)
    {
        boardMapper.insertBoard(board);
    }

    public List<Board> getBoardList(Criteria criteria)
    {
        return boardMapper.selectBoardList(criteria);
    }

    public int getBoardCount(Criteria criteria)
    {
        return boardMapper.selectTotalCount(criteria);
    }

    public Board getBoard(int id)
    {
        return boardMapper.selectBoard(id);
    }

    public Board getBoardByCommentId(int commentId)
    {
        return boardMapper.selectBoardByCommentId(commentId);
    }

    public void modifyBoard(Board board)
    {
        boardMapper.updateBoard(board);
    }

    public void removeBoard(int id)
    {
        boardMapper.deleteBoard(id);
    }

    public void addComment(Comment comment)
    {
        commentMapper.insertComment(comment);
    }

    public Comment getComment(int id)
    {
        return commentMapper.selectComment(id);
    }

    public List<Comment> getCommentList(int boardId)
    {
        return commentMapper.selectCommentList(boardId);
    }

    public void updateComment(Comment comment)
    {
        commentMapper.updateComment(comment);
    }

    public void removeComment(int id)
    {
        commentMapper.deleteComment(id);
    }
}