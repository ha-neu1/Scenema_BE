package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.BoardDTO;

@Repository
public class BoardDAO {
	
	@Autowired
	SqlSession session;
	
	//게시물 작성
	public int boardWriting(BoardDTO dto) {
		return session.insert("boardWriting", dto);
	}
	
	//게시물 목록
	public List<BoardDTO> getBoardList(BoardDTO dto) {
        return session.selectList("getBoardList", dto);
    }
	
	//게시물 조회
    public List<BoardDTO> getBoardListById(int boardid){
    	return session.selectList("getBoardListById", boardid);
    }
    
}
