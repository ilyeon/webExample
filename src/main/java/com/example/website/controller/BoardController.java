package com.example.website.controller;

import java.util.HashMap;
import java.util.List;

import com.example.website.model.Board;
import com.example.website.model.Comment;
import com.example.website.model.Criteria;
import com.example.website.model.LoginUserDetails;
import com.example.website.model.pageMaker;
import com.example.website.service.BoardService;
import com.example.website.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController
{
	@Autowired
	BoardService boardService;
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "/main")
	public String manePage(Authentication authentication, Model model)
	{
		if (authentication == null)
		{
			return "redirect:/login";
		}
		else
		{
			model.addAttribute("user", authentication.getPrincipal());
			return "main";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
	public HashMap<String, Object> getBoardList(Criteria criteria)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();

		pageMaker pageMaker = new pageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(boardService.getBoardCount(criteria));
		
		List<Board> list = boardService.getBoardList(criteria);

		map.put("list", list);
		map.put("pageMaker", pageMaker);
		return map;
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/newBoard", method = RequestMethod.GET)
	public String newBoard()
	{
		return "newBoard";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/createBoard", method = RequestMethod.POST)
	public String createBoard(Board board, @AuthenticationPrincipal LoginUserDetails userDetails)
	{
		board.setuserId(userDetails.getId());
		boardService.createBoard(board);

		return "redirect:/main";
	}

	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/board/{id}")
	public String boardDetail(@PathVariable("id") int id, Model model, @AuthenticationPrincipal LoginUserDetails userDetails)
	{
		int userId = userDetails.getId();
		Board board = boardService.getBoard(id);

		if(userId != board.getuserId())
		{
			board.setViewCount(board.getViewCount()+1);
			boardService.modifyBoard(board);
		}
		
		model.addAttribute("canModify", (board.getuserId() == userId));
		model.addAttribute("board", boardService.getBoard(id));
		model.addAttribute("user", userDetails.getId());
		
		return "boardDetail";
	}

	@PreAuthorize("isMyBoard(#id)")
	@RequestMapping(value = "modifyBoard/{id}", method = RequestMethod.GET)
	public String modifyBoard(@PathVariable("id") int id, Model model)
	{
		model.addAttribute("board", boardService.getBoard(id));
		return "modifyBoard";
	}

	@ResponseBody
	@RequestMapping(value = "updateBoard", method = RequestMethod.POST)
	public int updateBoard(Board board, @AuthenticationPrincipal LoginUserDetails userDetails) throws Exception
	{
		Board updateBoard = boardService.getBoard(board.getId());

		if(updateBoard.getuserId() != userDetails.getId())
		{
			return 0;
		}
		else
		{
			boardService.modifyBoard(board);
			return 1;
		}
	}

	@ResponseBody
	@PreAuthorize("isMyBoard(#id)")
	@RequestMapping(value = "deleteBoard")
	public int removeBoard(@RequestParam("id") int id) 
	{
		Board board = boardService.getBoard(id);

		if(board != null)
		{
			boardService.removeBoard(id);
			return 1;
		}
		
		return 0;
	}

	@ResponseBody
	@RequestMapping(value = "addComment", method = RequestMethod.POST)
	public void addComment(Comment comment, @AuthenticationPrincipal LoginUserDetails userDetails)
	{
		comment.setUserId(userDetails.getId());
		boardService.addComment(comment);
	}

	@ResponseBody
	@RequestMapping(value = "commentList", method = RequestMethod.POST)
	public List<Comment> getCommentList(Comment comment)
	{
		return boardService.getCommentList(comment.getBoardId());
	}

	@ResponseBody
	@PreAuthorize("isMyComment(#id)")
	@RequestMapping(value = "modifyComment", method = RequestMethod.POST)
	public void modifyComment(@RequestParam("id") int id, @RequestParam("comment") String comment, @AuthenticationPrincipal LoginUserDetails userDetails)
	{
		Comment updateComment = boardService.getComment(id);
		if(updateComment != null && (updateComment.getUserId() == userDetails.getId()))
		{
			updateComment.setComment(comment);
			boardService.updateComment(updateComment);
		}
	}

	@ResponseBody
	@PreAuthorize("isMyComment(#id)")
	@RequestMapping(value = "deleteComment", method=RequestMethod.POST)
	public void removeComment(@RequestParam("id") int id, @AuthenticationPrincipal LoginUserDetails userDetails)
	{
		Comment comment = boardService.getComment(id);

		if(userDetails.getId() == comment.getUserId())
		{
			boardService.removeComment(id);
		}
	}
}