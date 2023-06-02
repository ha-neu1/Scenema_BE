package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MovieDAO;
import dto.MovieDTO;

@Service
public class MovieDBService {
	@Autowired
	MovieDAO dao;
	
	public int insertMovieDB(MovieDTO dto) {
		return dao.insertMovieDB(dto);
	}
	
	public MovieDTO getMovieFromID(int movieid) {
		return dao.getMovieFromID(movieid);
	}
	
}
