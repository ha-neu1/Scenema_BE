package dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.BoardDTO;

@Repository("boardWritingDao")
public class BoardWritingDAO {
	
	@Autowired
	SqlSession session;
	
	public int boardWriting(BoardDTO dto) {
		return session.insert("boardWriting", dto);
	}
	
}
