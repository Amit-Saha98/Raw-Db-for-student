package com.example.mydb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    DbManager dbManager;

    @GetMapping("/connect-db")
    public void getConnection(@RequestParam String user, @RequestParam String pass) throws SQLException {
        dbManager.getConnection(user,pass);
    }

    @PostMapping("/insert")
    public void insertDb(@RequestBody Student s) throws SQLException {
        dbManager.insertInfo(s);
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<Student>> getAllStudent() throws SQLException {
        List<Student>ans = dbManager.getAllStudents();
        return new ResponseEntity<>(ans, HttpStatus.ACCEPTED);
    }
}
