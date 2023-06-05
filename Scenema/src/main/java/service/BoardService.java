package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BoardDAO;
import dto.BoardDTO;

@Service
public class BoardService {
	@Autowired
	BoardDAO boardDAO;
	
	public List<BoardDTO> getBoardList() {
        return boardDAO.getBoardList();
    }

    public BoardDTO getBoardById(int boardid) {
        return boardDAO.getBoardById(boardid);
    }
}
