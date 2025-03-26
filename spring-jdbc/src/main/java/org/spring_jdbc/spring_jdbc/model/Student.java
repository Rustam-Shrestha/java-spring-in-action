package org.spring_jdbc.spring_jdbc.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component

//since we are going to have multiple student to work we need prototype scope not singleton
@Scope("prototype")
public class Student {
    private int rollno;
    private String name;
    private int marks;

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "rollno=" + rollno +
                ", name='" + name + '\'' +
                ", marks=" + marks +
                '}';
    }
}
