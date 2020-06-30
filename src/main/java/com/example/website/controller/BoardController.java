package com.example.website.controller;

import java.util.HashMap;
import java.util.List;

import com.example.website.model.Board;
import com.example.website.model.Comment;
import com.example.website.model.LoginUserDetails;
import com.example.website.model.User;
import com.example.website.service.BoardService;
import com.example.website.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController
{
    @Autowired
    BoardService boardService;
    
    @Autowired
    UserService userService;

    @RequestMapping(value = "/main")
    public String manePage(Authentication authentication)
    {
        if (authentication == null)
		{
			return "redirect:/login";
		}
		else
		{
			return "main";
		}
    }
    
    @ResponseBody
    @RequestMapping(value = "/boardList", method = RequestMethod.GET)
    public List<HashMap<String, Object>> getBoardList()
    {
        List<HashMap<String, Object>> list = boardService.getBoardList();
        return list;
    }


    @RequestMapping(value = "/newBoard", method = RequestMethod.GET)
    public String newBoard()
    {
        return "newBoard";
    }

    @RequestMapping(value = "/createBoard", method = RequestMethod.POST)
    public String createBoard(Board board, Authentication authentication)
    {
        LoginUserDetails userDetails = (LoginUserDetails) authentication.getPrincipal();
        
        board.setuserId(userDetails.getId());
        boardService.createBoard(board);

        return "redirect:/main";
    }

    @RequestMapping(value = "/board/{id}")
    public String boardDetail(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("board", boardService.getBoard(id));
        return "boardDetail";
    }

    @ResponseBody
    @RequestMapping(value = "addComment", method = RequestMethod.POST)
    public void addComment(Comment comment, Authentication authentication)
    {
        LoginUserDetails userDetails = (LoginUserDetails) authentication.getPrincipal();

        comment.setUserId(userDetails.getId());
        boardService.addComment(comment);
    }
}