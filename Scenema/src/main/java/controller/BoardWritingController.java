package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import dto.BoardDTO;
import service.BoardWritingService;

@Controller
public class BoardWritingController {

	@Autowired
	BoardWritingService service;

	@GetMapping("/boardwriting")
	public String writingform() {
		return "writingform";
	}

	@PostMapping("/boardwriting")
	public ModelAndView writingprocess(BoardDTO dto) {
		
		int insert = service.boardWriting(dto);
		ModelAndView mv = new ModelAndView();
		mv.addObject("insert", insert);
		mv.setViewName("writingform");
		return mv;

	}

	@GetMapping("/boardlist")
	public String boardlist() {
		return "boardlist";
	}
}
