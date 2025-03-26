package org.spring_jdbc.spring_jdbc.service;


import org.spring_jdbc.spring_jdbc.model.Student;
import org.spring_jdbc.spring_jdbc.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepo studentRepo;

    public StudentRepo getStudentRepo() {
        return studentRepo;
    }
    @Autowired
    public void setStudentRepo(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public void addStudent(Student rustam) {
        studentRepo.save(rustam);
    }

    public List<Student> getStudent(){
        return studentRepo.findAll();
    }
}
