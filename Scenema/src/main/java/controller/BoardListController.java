package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dto.BoardDTO;
import service.BoardService;

@Controller
public class BoardListController {
	@Autowired
	BoardService service;
	ModelAndView mv = new ModelAndView();

	// 게시글 전체 목록 조회
	@RequestMapping("/boardlist")
	public ModelAndView getBoardList() {
		List<BoardDTO> boardList = service.getBoardList(null);
		mv.addObject("BoardList", boardList);
		mv.setViewName("BoardList");
		return mv;
	}

	// 해당 아이디의 게시글 조회
	@RequestMapping("/boardlist/{boardid}")
	public ModelAndView getBoardListById(@PathVariable int boardid) {
		List<BoardDTO> boardListById = service.getBoardListById(boardid);
		mv.addObject("BoardListById", boardListById);
		mv.setViewName("BoardListById");
		return mv;
	}

}
