package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import dto.BoardDTO;
import service.BoardService;


@Controller
public class BoardController {
	 @Autowired
	 BoardService boardService;
	 
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
		        ModelAndView mav = new ModelAndView();
		        mav.addObject("BoardRead", board);
		        mav.setViewName("BoardRead");
		        return mav;
		        
		    }
	    
}
