package dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.UserDTO;

@Repository
public class LoginDAO {
	@Autowired
	SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}

	public UserDTO getUser(String userid) {
		return session.selectOne("getUserlogin", userid);
	}
	
}
