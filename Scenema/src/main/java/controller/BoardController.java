package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dto.BoardCommentDTO;
import dto.BoardDTO;
import service.BoardCommentService;
import service.BoardService;


@Controller
public class BoardController {
	 @Autowired
	 BoardService boardService;
	 
	 @Autowired
	 BoardCommentService commentService;
	 
	//게시글 목록 조회
		 @GetMapping("/boardList")
		    public ModelAndView getBoardList() {
		        List<BoardDTO> boardList = boardService.getBoardList();
		        ModelAndView mav = new ModelAndView();
		        mav.addObject("BoardList", boardList);
		        mav.setViewName("BoardList");
		        return mav;
		    }

		 //게시글 조회
		 @GetMapping("/boardRead/{boardid}")
		    public ModelAndView getBoardById(@PathVariable int boardid) {
		        BoardDTO board = boardService.getBoardById(boardid);
		        List<BoardCommentDTO> comments = commentService.getCommentsByBoardId(boardid);
		        ModelAndView mav = new ModelAndView("BoardRead");
		        mav.addObject("BoardRead", board);
		        mav.addObject("comments", comments);
		        return mav;
		    }
		 
		 //댓글 추가
		 @PostMapping("/boardRead/{boardid}/comment")
		 public String addComment(@PathVariable int boardid, @RequestParam("userid") String userid, @RequestParam("bcCreateAt") String bcCreateAt,  @RequestParam("contents") String contents) {
			 BoardCommentDTO comment = new BoardCommentDTO();
			 comment.getBoardid();
			 comment.getUserid();
			 comment.getBcCreateAt();
			 comment.getContents();
			 
			 commentService.insertBoardComment(comment);
			 
			 return "redirect:/boardRead/" + boardid;
		 }
	    
}
