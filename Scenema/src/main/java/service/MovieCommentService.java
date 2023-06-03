package service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MovieCommentDAO;
import dto.MovieCommentDTO;

@Service
public class MovieCommentService {
	@Autowired
	MovieCommentDAO dao;
	
	public int insertMovieComment(MovieCommentDTO dto) {
		return dao.insertMovieComment(dto);
	}
	
	public List<MovieCommentDTO> getMovieComments(int movieid) {
		return dao.getMovieComments(movieid);
	}
	
	public double getMovieScore(int movieid) {
		return dao.getMovieScore(movieid);
	}
	
	public int getCommentsCount(int movieid) {
		return dao.getCommentsCount(movieid);
	}
	
	public List<MovieCommentDTO> getPagingComments(HashMap<String, Integer> map){
		return dao.getPagingComments(map);
	}
}
