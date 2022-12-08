package com.example.mydb;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class DbManager {

    public Connection connection;

    public DbManager()throws SQLException{
        //createTable();
    }

    public Connection getConnection(String user , String password) throws SQLException {
        if(connection==null){
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentDb",user,password);
            createTable();
        }
        return connection;
    }
    public void createTable() throws SQLException {
        String sql = "create table if not exists student_info(id INT primary key auto_increment,age INT, name VARCHAR(30))";
        Statement st = connection.createStatement();
        st.execute(sql);
    }
    public void insertInfo(Student s) throws SQLException {
        String sql = "insert into Student_info(age,name) values ("+s.age+",'"+s.name+"')";
        Statement st = connection.createStatement();
        st.executeUpdate(sql);
    }
    public List<Student> getAllStudents() throws SQLException {
        String sql = "select *  from student_info;";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);

        List<Student>ans = new ArrayList<>();
        while(rs.next()){
            Student student = new Student(rs.getInt("age"),rs.getString("name"));
            ans.add(student);
        }
        return ans;
    }
}
