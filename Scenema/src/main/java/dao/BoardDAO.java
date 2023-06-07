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
	
	//�Խù� �ۼ�
	public int boardWriting(BoardDTO dto) {
		return session.insert("boardWriting", dto);
	}
	
	//�Խù� ���
	public List<BoardDTO> getBoardList(BoardDTO dto) {
        return session.selectList("getBoardList", dto);
    }
	
	//�Խù� ��ȸ
    public List<BoardDTO> getBoardListById(int boardid){
    	return session.selectList("getBoardListById", boardid);
    }

    public int boardViewUp(int boardid){
    	return session.update("boardViewUp", boardid);
    }

    public int boardUpdate(BoardDTO dto){
    	return session.update("boardUpdating", dto);
    }

    public int boardDelete(int boardid){
    	return session.delete("boardDelete", boardid);
    }
    
    
    
}
