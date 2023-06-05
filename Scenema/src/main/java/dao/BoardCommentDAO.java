package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.BoardCommentDTO;

@Repository
public class BoardCommentDAO {
	@Autowired
	SqlSession sqlSession;
	
	public List<BoardCommentDTO> getCommentsByBoardId(int boardid){
		return sqlSession.selectList("comment.getCommentsByBoardId", boardid);
	}

    public void insertBoardComment(BoardCommentDTO comment) {
    	 sqlSession.insert("comment.insertBoardComment", comment);
    }

}
