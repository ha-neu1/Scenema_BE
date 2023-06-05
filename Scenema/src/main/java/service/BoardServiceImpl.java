package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BoardDAO;
import dto.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDAO dao;
	
	@Override
	public int boardWriting(BoardDTO dto) {
		return dao.boardWriting(dto);
	}

	@Override
	public List<BoardDTO> getBoardList(BoardDTO dto) {
		return dao.getBoardList(dto);
	}

	@Override
	public List<BoardDTO> getBoardListById(int boardid) {
		return dao.getBoardListById(boardid);
	}
	
}
