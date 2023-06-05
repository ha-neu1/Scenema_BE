package service;

import java.util.List;

import dto.BoardDTO;

public interface BoardService {
	public int boardWriting(BoardDTO dto);
	public List<BoardDTO> getBoardList(BoardDTO dto);
	public List<BoardDTO> getBoardListById(int boardid);
	
}
