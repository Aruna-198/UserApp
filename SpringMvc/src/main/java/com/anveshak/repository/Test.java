package com.anveshak.repository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("/SpringMvc/src/main/webapp/WEB-INF/spring-mvc-servlet.xml");
		UserDao userdao=(UserDao) context.getBean("UserDao");
		com.anveshak.pojo.User user=new com.anveshak.pojo.User();
		//user.setUid(1);
		user.setFirstName("Aruna");
		user.setLastName("Matre");
		user.setGender("female");
		
		String status=userdao.addUser(user);
		if(status.equalsIgnoreCase("success")) {
			System.out.println("user add");
		}
		else
			System.out.println("not");
		
	}

}
