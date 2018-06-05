package com.example.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.entity.StudentEntity;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Long> {

    @Query (value = "select * from student", nativeQuery = true)
    List<StudentEntity> findAllStudents();

}
