package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.BoardDTO;

@Repository
public class BoardDAO {
	@Autowired
	SqlSession sqlSession;
	
	public List<BoardDTO> getBoardList() {
        return sqlSession.selectList("getBoardList");
    }

    public BoardDTO getBoardById(int boardid) {
        return sqlSession.selectOne("getBoardById", boardid);
    }

}
