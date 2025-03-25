package com.rustam.SpringBootInAction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootInActionApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootInActionApplication.class,args);
		Student student = context.getBean(Student.class);
		student.code();
	}

}
