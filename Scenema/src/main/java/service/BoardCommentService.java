package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BoardCommentDAO;
import dto.BoardCommentDTO;

@Service
public class BoardCommentService {
	@Autowired
	BoardCommentDAO commentDAO;
	
	public List<BoardCommentDTO> getCommentsByBoardId(int boardid){
		return commentDAO.getCommentsByBoardId(boardid);
	}
	
	public void insertBoardComment(BoardCommentDTO comment) {
		commentDAO.insertBoardComment(comment);
	}
}
