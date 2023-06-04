package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BoardWritingDAO;
import dto.BoardDTO;

@Service
public class BoardServiceImpl implements BoardWritingService {
	
	@Autowired
	BoardWritingDAO dao;
	
	@Override
	public int boardWriting(BoardDTO dto) {
		return dao.boardWriting(dto);
	}	
	
}
