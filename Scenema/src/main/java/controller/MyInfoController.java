package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dto.MovieDTO;
import dto.MovieLikeDTO;
import dto.UserDTO;
import service.LoginService;
import service.MovieDBService;
import service.MovieLikeService;

@Controller
public class MyInfoController {
	@Autowired
	LoginService service;
	@Autowired
	MovieLikeService service_like;
	@Autowired
	MovieDBService service_db;
	
	@RequestMapping("/myinfo")
	public ModelAndView myInfo(HttpSession session) {
		String userid = (String) session.getAttribute("userid");
		UserDTO loginuser = service.getUser(userid);
		List<MovieLikeDTO> likemovielist = service_like.listMovieLikeByUser(userid);
		ArrayList<MovieDTO> movieDBlist = new ArrayList<MovieDTO>();
		
		System.out.println("★★★★★★★★★★★★★★★★★");
		System.out.println("★★★★★★★★★★★★★★★★★");
		
		for(MovieLikeDTO likedto : likemovielist) {
			MovieDTO moviedto = service_db.getMovieFromID(likedto.getMovieid());
			movieDBlist.add(moviedto);
		}
		
		for(MovieDTO dto : movieDBlist) {
			String poster = dto.getPosterurl().split("\\|")[0];
			dto.setPosterurl(poster);
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("loginUser", loginuser);
		mv.addObject("movieDBlist", movieDBlist);
		mv.setViewName("MyInfo");
		return mv;
	}
	
	@GetMapping("/updateuser")
	public ModelAndView myInfoupdate(HttpSession session) {
		UserDTO loginuser = service.getUser((String) session.getAttribute("userid"));
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("loginUser", loginuser);
		mv.setViewName("MyInfoUpdate");
		return mv;
	}
	
	@PostMapping("/updateuser")
	public @ResponseBody String myInfoupdatesql(UserDTO dto) {
		service.userUpdate(dto);
		return "";
	}
	
	@RequestMapping("/deleteuser")
	public @ResponseBody String deleteUser(HttpSession session) {
		String id = (String) session.getAttribute("userid");
		service.deleteUser(id);
		return "";
	}
	
	
}
