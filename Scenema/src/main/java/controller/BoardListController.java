package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dto.BoardCommentDTO;
import dto.BoardDTO;
import service.BoardCommentService;
import service.BoardService;

@Controller
public class BoardListController {
	@Autowired
	BoardService service;
	BoardCommentService commentService;
	ModelAndView mv = new ModelAndView();

	// �Խñ� ��ü ��� ��ȸ
	@RequestMapping("/boardlist")
	public ModelAndView getBoardList() {
		List<BoardDTO> boardList = service.getBoardList(null);
		mv.addObject("BoardList", boardList);
		mv.setViewName("BoardList");
		return mv;
	}

	// �ش� ���̵��� �Խñ� �� ��� ��ȸ
	@RequestMapping("/boarddetail")
	public ModelAndView getBoardListById(int boardid) {
		List<BoardDTO> boardListById = service.getBoardListById(boardid);
//		List<BoardCommentDTO> boardCommentById = commentService.getCommentsByBoardId(boardid);
		mv.addObject("BoardListById", boardListById);
//		mv.addObject("BoardCommentById", boardCommentById);
		mv.setViewName("BoardListById");
		return mv;
	}
	
	@PostMapping("/addcomment")
	public String commentWriting(BoardCommentDTO comment, @RequestParam(required = false) int boardid ) {
		int insert = commentService.insertBoardCommnet(comment);
		return "redirect:/boarddetail?boardid=" + boardid;
	}

}
