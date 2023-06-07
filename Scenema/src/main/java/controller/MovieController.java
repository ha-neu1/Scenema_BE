package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import dto.MovieDTO;
import main.MovieAPI;
import service.MovieDBService;

@Controller
public class MovieController {
	@Autowired
	MovieDBService service;

    @GetMapping("/movielist")
    public ModelAndView main() {
    	ArrayList<MovieDTO> movielist = new ArrayList<MovieDTO>();
    	
    	movielist.add(service.getMovieFromID(55));
    	movielist.add(service.getMovieFromID(53));
    	movielist.add(service.getMovieFromID(251));
    	movielist.add(service.getMovieFromID(108));
    	movielist.add(service.getMovieFromID(229));
    	
    	movielist.add(service.getMovieFromID(118));
    	movielist.add(service.getMovieFromID(248));
    	movielist.add(service.getMovieFromID(259));
    	movielist.add(service.getMovieFromID(82));
    	movielist.add(service.getMovieFromID(95));
    	
    	movielist.add(service.getMovieFromID(56));
    	movielist.add(service.getMovieFromID(88));
    	movielist.add(service.getMovieFromID(93));
    	movielist.add(service.getMovieFromID(213));
    	movielist.add(service.getMovieFromID(172));

    	movielist.add(service.getMovieFromID(64));
    	movielist.add(service.getMovieFromID(28));
    	movielist.add(service.getMovieFromID(195));
    	movielist.add(service.getMovieFromID(169));
    	movielist.add(service.getMovieFromID(248));
    	
    	for(MovieDTO dto : movielist) {
    		String poster = dto.getPosterurl().split("\\|")[0];
    		dto.setPosterurl(poster);
    	}
    	
    	ModelAndView mv = new ModelAndView();
    	mv.addObject("movielist",movielist);
    	mv.setViewName("movielist");
        return mv;
    }
}