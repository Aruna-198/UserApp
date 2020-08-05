package com.anveshak.repository;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class JdbcConfig {
	@Bean(name= {"ds"})
	public DataSource getDataSource() {
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/user");
		ds.setUsername("root");
		ds.setPassword("root");
		return ds;
		
	}
	@Bean(name= {"jdbcTemplate"})
	public JdbcTemplate getTemplate() {
		JdbcTemplate jdbcTemplate=new JdbcTemplate();
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate;
		
	}
	@Bean(name= {"UserDao"})
	public UserDao getUserDao() {
		UserDaoImpl userDao=new UserDaoImpl();
		userDao.setJdbcTemplate(getTemplate());
		return userDao;
		
	}
	

}
