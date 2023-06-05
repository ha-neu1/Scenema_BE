package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.BoardDTO;

@Repository("boardDao")
public class BoardDAO {
	
	@Autowired
	SqlSession session;
	
	public int boardWriting(BoardDTO dto) {
		return session.insert("boardWriting", dto);
	}
	
	public List<BoardDTO> getBoardList(BoardDTO dto) {
        return session.selectList("getBoardList", dto);
    }

    public List<BoardDTO> getBoardListById(int boardid){
    	return session.selectOne("getBoardListById", boardid);
    }
	
}
