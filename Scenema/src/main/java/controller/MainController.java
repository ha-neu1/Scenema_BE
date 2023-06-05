package controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import dto.MovieDTO;
import main.MovieAPI;
import service.MovieDBService;

@Controller
public class MainController {
	@Autowired
	MovieDBService service;

    @GetMapping("/")
    public ModelAndView main() {
    	//api호출
    	MovieAPI api = new MovieAPI();
    	String boxoffice = api.requestAPI();
    	
    	//movielist DB호출
    	String [] movies = boxoffice.split("\\|");
    	ArrayList<MovieDTO> movielist = new ArrayList<MovieDTO>();
    	
    	for(String title : movies) {
    		MovieDTO dto = service.getMovieFromTitle(title);
    		String poster = dto.getPosterurl().split("\\|")[0];
    		dto.setPosterurl(poster);
    		movielist.add(dto);
    	}
    	
    	ModelAndView mv = new ModelAndView();
    	mv.addObject("boxofficelist",movielist);
    	mv.setViewName("main");
        return mv;
    }
}