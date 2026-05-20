package com.example.studentapi.service;

import com.example.studentapi.entity.Student;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StudentService {

    List<Student> getAll();
    Optional<Student> getById(Long id);
    Student create(Student student);
    Student update(Long id, Student student);
    Student patch(Long id, Map<String, Object> updates);
    void delete(Long id);
}
