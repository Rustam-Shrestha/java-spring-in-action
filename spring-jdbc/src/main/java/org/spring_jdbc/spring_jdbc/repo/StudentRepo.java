package org.spring_jdbc.spring_jdbc.repo;

import org.spring_jdbc.spring_jdbc.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepo {


    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Student rustam){
        String sql = "INSERT INTO student (rollno, name, marks) VALUES(?,?,? )";
        int rows = jdbcTemplate.update(sql,rustam.getRollno(),rustam.getName(), rustam.getMarks());
        if(rows>0){
            System.out.println(rows + " rows manipulated");
        }else{
            System.out.println("query problem");
        }
    }

    public List<Student> findAll() {
        String sql = "SELECT * FROM student";
        RowMapper<Student> mapper = new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student s = new Student();
                s.setRollno(rs.getInt("rollno"));
                s.setName(rs.getString("name"));
                s.setMarks(rs.getInt("marks"));
                return s;
            }
        };
        return jdbcTemplate.query(sql, mapper);
    }
}
