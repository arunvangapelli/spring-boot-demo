package com.example.spring.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.spring.entity.StudentEntity;
import com.example.spring.repository.StudentRepository;

@Component
public class StudentDao {
    
    @Autowired
    private StudentRepository studentRepository;
    
    public List<StudentEntity> getStudents() {
        
        return studentRepository.findAllStudents();
    }

}
