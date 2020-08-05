package com.anveshak.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.anveshak.pojo.User;

public class UserDaoImpl implements UserDao {
	private JdbcTemplate jdbcTemplate;
	String status="";
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public String addUser(User user) {
		String sql="insert into table_User values("+user.getUid()+","+user.getFirstName()+","
                +user.getLastName()+","+user.getGender()+ ","+user.getDob()+")";
		int count=jdbcTemplate.update(sql);
		if(count==1) {
			status="success";
		}
		else {
			status="fail";
		}
		
		return status;
	}

	public User getUser(String id) {
		
		return null;
	}

	public String deleteUser(String id) {
		String sql="select*from table_User where id="+id+"";
		List<User>users=jdbcTemplate.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user=new User();
				//user.setUid(rs.getInt("UID"));
				user.setFirstName(rs.getString("FNAME"));
				user.setLastName(rs.getString("LNAME"));
				user.setGender(rs.getString("GENDER"));
				
				//user.setDob(rs.getStirng("DOB"));
				return user;
			}
		});
		if(users.isEmpty()) {
			
				status=" NotExised";
			}
			else {
					String sql1="delete from table_user where id="+id+"";
					int count=jdbcTemplate.update(sql1);
					if(count==1) {
						status="Deleted";
					}
					else {
						status="fail";
					}
		}
		
		return status;
				
	}

	public List<User> getAllUser() {
		String sql="select*from table_User";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<User>>() {
			@Override
			public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<User>list=new ArrayList<User>();
				while(rs.next()) {
					User user=new User();
					String id=rs.getString("UID");
					String fname=rs.getString("FNAME");
					String lname=rs.getString("LNAME");
					String gender=rs.getString("GENDER");
					String date=rs.getString("DOB");
					user.setUid(id);
					user.setFirstName(fname);
					user.setLastName(lname);
					user.setGender(gender);
					user.setDob(date);
					list.add(user);	
					
				}
				
				return list;
			}
		});
	}

	public String updateUser(User user) {
		String sql="update table_User set FNAME="+user.getFirstName()+",LNAME="+user.getLastName()+",GENDER="+user.getGender()+",DOB="+user.getDob()+" where UID="+user.getUid()+"";
		int count=jdbcTemplate.update(sql);
		if(count==1) {
			status="success";
		}else
			status="fail";
		return status;
	}

		
	}

	

