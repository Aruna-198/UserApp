package com.anveshak.repository;

import org.apache.ibatis.session.SqlSession;

import com.anveshak.model.User;

public class UserDaoImpl implements UserDao {

	  private SqlSession sqlSession;

	  public void setSqlSession(SqlSession sqlSession) {
	    this.sqlSession = sqlSession;
	  }


	@Override
	public int addUser(User user) {
			int count=sqlSession.insert("com.anveshak.repository.UserMapper.addUser",user);
			return count;
			
	}

	@Override
	public java.util.List<User> getAllUser() {
		return sqlSession.selectList("com.anveshak.repository.UserMapper.getAllUsers");
			}

	@Override
	public int updateUser(User user) {
		int count=sqlSession.update("com.anveshak.repository.UserMapper.updateUser", user);
		return count;
	}

	@Override
	public int deleteUser(String email) {
		int count=sqlSession.delete("com.anveshak.repository.UserMapper.deleteUser", email);
		return count;
	}

	@Override
	public User getUser(String email) {
		User user=(User) sqlSession.selectOne("com.anveshak.repository.UserMapper.getUser", email);
		return user;
		
	}

}