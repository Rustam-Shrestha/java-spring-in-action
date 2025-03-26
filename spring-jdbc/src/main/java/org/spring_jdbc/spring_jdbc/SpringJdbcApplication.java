package org.spring_jdbc.spring_jdbc;

import org.spring_jdbc.spring_jdbc.model.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringJdbcApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringJdbcApplication.class, args);
		ApplicationContext context = SpringApplication.run(SpringJdbcApplication.class,args);
		Student rustam = context.getBean(Student.class);
		rustam.setRollno(13);
		rustam.setMarks(75);
		rustam.setName("Rustam");

//		addStudent(rustam);
	}

}
