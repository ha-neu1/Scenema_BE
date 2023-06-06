package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MovieLikeDAO;
import dto.MovieLikeDTO;

@Service
public class MovieLikeService {
	@Autowired
	MovieLikeDAO dao;
	
	public int isMovieLike(MovieLikeDTO dto) {
		return dao.isMovieLike(dto);
	}
	
	public int insertMovieLike(MovieLikeDTO dto) {
		return dao.insertMovieLike(dto);
	}
	
	public int deleteMovieLike(MovieLikeDTO dto) {
		return dao.deleteMovieLike(dto);
	}
	
	public int countMovieLike(int movieid) {
		return dao.countMovieLike(movieid);
	}
	
}