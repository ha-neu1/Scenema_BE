package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dto.MovieDTO;
import service.MovieDBService;

@Controller
public class DetailController {
	@Autowired
	MovieDBService service;
	
	@RequestMapping("/detailpage")
	public ModelAndView detailPage(int movieid) {
		MovieDTO dto = service.getMovieFromID(movieid);
		
		//포스터 및 스틸컷 하나씩 별도 분리
		String posters [] = dto.getPosterurl().split("\\|");
		String stillcuts [] = dto.getStillcuturls().split("\\|");
		
		//날짜 형식 바꾸기
		String year = dto.getReleaseDate().substring(0, 4);
		String month = dto.getReleaseDate().substring(4, 6);
		String day = dto.getReleaseDate().substring(6, 8);
		dto.setReleaseDate(year+"."+month+"."+day);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("movie",dto);
		mv.addObject("mainposter", posters[0]);
		mv.addObject("mainstillcut", stillcuts[0]);
//		mv.setViewName("test");
		mv.setViewName("DetailPage");
		return mv;
	}
}
