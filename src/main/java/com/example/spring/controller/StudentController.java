package com.example.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.spring.entity.StudentEntity;
import com.example.spring.service.StudentService;

@Controller
@RequestMapping("/example/v1/students")
public class StudentController {
    
    @Autowired
    private StudentService studentService;
    
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<StudentEntity> getStudents() {
        
        return studentService.getStudents();
    }
}
