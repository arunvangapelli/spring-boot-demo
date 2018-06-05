package com.example.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.dao.StudentDao;
import com.example.spring.entity.StudentEntity;

@Service
public class StudentService {
    
    @Autowired
    private StudentDao studentDao;
    
    public List<StudentEntity> getStudents() {
        
        return studentDao.getStudents();
    }

}
