package com.anveshak.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.anveshak.pojo.User;

public class UserDaoImpl implements UserDao {
	private JdbcTemplate jdbcTemplate;
	String status="";
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	@Override
	public String addUser( final User user) {
		 User existingUser=getUser(user.getEmail());
		 if(existingUser==null) {
		final String sql = "insert into table_User( FNAME,LNAME,EMAIL,PASSWORD,MOBILENUM,GENDER,DOB) values(?,?,?,?,?,?,?)";
				KeyHolder holder = new GeneratedKeyHolder();
				int i=jdbcTemplate.update(new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
						//ps.setInt(1, findId());
						ps.setString(1, user.getFirstName());
						ps.setString(2, user.getLastName());
						ps.setString(3, user.getEmail());
						ps.setString(4, user.getPassword());
						ps.setString(5, user.getMobileNumber());
						ps.setString(6, user.getGender());
						ps.setString(7, user.getDob());
						return ps;
					}
				}, holder);

			if(i==1) 
				status="success";
				else 
					status="fails";
		 }else {
			 status="exist";}
			return status;
				
			
			}
    @Override
	public String deleteUser(String email) {
    	
    	String sql = "select * from table_User where EMAIL='"+email+"';";		
    	List<User>users=jdbcTemplate.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user=new User();
				
				user.setFirstName(rs.getString("FNAME"));
				user.setLastName(rs.getString("LNAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setMobileNumber(rs.getString("PASSWORD"));
				user.setGender(rs.getString("GENDER"));
				user.setDob(rs.getString("DOB"));
				return user;
			}
		});
		if(users.isEmpty()) {
				status=" NotExisted";
			}
			else {
					String sql1="delete from table_user where EMAIL='"+email+"';";
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
					int id=rs.getInt("UID");
					String fname=rs.getString("FNAME");
					String lname=rs.getString("LNAME");
					String email=rs.getString("EMAIL");
					String pass=rs.getString("PASSWORD");
					String mobileNumber=rs.getString("MOBILENUM");
					String gender=rs.getString("GENDER");
					String date=rs.getString("DOB");
					user.setId(id);
					user.setFirstName(fname);
					user.setLastName(lname);
					user.setEmail(email);
					user.setPassword(pass);
					user.setMobileNumber(mobileNumber);
					user.setGender(gender);
					user.setDob(date);
					list.add(user);	
				}
				return list;
				
			}
		});
	}
	@Override
	public String updateUser(User user) {
		String sql = "update table_user set FNAME = '" + user.getFirstName() + "', LNAME = '" + user.getLastName()+"', EMAIL = '" +user.getEmail()+"', PASSWORD = '" + user.getPassword()+"', MOBILENUM = '" + user.getMobileNumber()+"', GENDER = '" + user.getGender()+"', DOB = '" + user.getDob()
        + "' where EMAIL = '" + user.getEmail() + "'";
		 
		/*String sql="update table_User set FNAME="+user.getFirstName()+",LNAME="+user.getLastName()+",EMAIL="+user.getEmail()+",PASSWORD="+user.getPassword()+",MOBILENUM="+user.getMobileNumber()+",GENDER="+user.getGender()+",DOB="+user.getDob()+" where EMAIL='"+user.getEmail()+"';";*/
		int count=jdbcTemplate.update(sql);
		if(count==1) {
			status="success";
		}else
			status="fail";
		return status;
		
	}

	@Override
	public User getUser(String email) {
		User user=new User();
		String sql = "select * from table_User where EMAIL='"+email+"';";		
    	List<User>users=jdbcTemplate.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user=new User();
				user.setId(rs.getInt("UID"));
				user.setFirstName(rs.getString("FNAME"));
				user.setLastName(rs.getString("LNAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setMobileNumber(rs.getString("MOBILENUM"));
				user.setGender(rs.getString("GENDER"));
				user.setDob(rs.getString("DOB"));
				return user;
			}
		});
		if(users.isEmpty()) {
				user=null;
			}
			else {
				user=users.get(0);
			}
		return user;
		}

}

	

