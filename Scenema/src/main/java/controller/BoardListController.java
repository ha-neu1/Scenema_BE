package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
		
		for(BoardDTO dto : boardList) {
			String str = dto.getCreatAt();
			str = str.substring(0, str.indexOf(" "));
			dto.setCreatAt(str);
		}
		
		mv.addObject("BoardList", boardList);
		mv.setViewName("BoardList");
		return mv;
	}
	// �ش� ���̵��� �Խñ� �� ��� ��ȸ
	@RequestMapping("/boarddetail")
	public ModelAndView getBoardListById(int boardid) {
		service.boardViewUp(boardid);
		List<BoardDTO> boardListById = service.getBoardListById(boardid);
		
//		List<BoardCommentDTO> boardCommentById = commentService.getCommentsByBoardId(boardid);
		
		mv.addObject("BoardListById", boardListById);
//		mv.addObject("BoardCommentById", boardCommentById);
		mv.setViewName("BoardListById");
		return mv;
	}
	
	@RequestMapping("/boarddelete")
	public @ResponseBody String boarddelete(int boardid) {
		service.boardDelete(boardid);
		return "";
	}

	@GetMapping("/boardupdate")
	public ModelAndView boardupdate(int boardid) {
		List<BoardDTO> boardListById = service.getBoardListById(boardid);
		mv.addObject("BoardListById", boardListById);
		mv.setViewName("UpdatingForm");
		return mv;
	}
	
	@PostMapping("/boardupdate")
	public @ResponseBody BoardDTO boardupdate(BoardDTO dto) {
		service.boardUpdate(dto);
		return dto;
	}
	
	
/*
	
	@PostMapping("/addcomment")
	public String commentWriting(BoardCommentDTO comment, @RequestParam(required = false) int boardid ) {
		int insert = commentService.insertBoardCommnet(comment);
		return "redirect:/boarddetail?boardid=" + boardid;
	}
*/
}
