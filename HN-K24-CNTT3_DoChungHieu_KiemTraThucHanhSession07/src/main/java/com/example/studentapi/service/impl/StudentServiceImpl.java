package com.example.studentapi.service.impl;

import com.example.studentapi.entity.Student;
import com.example.studentapi.repository.StudentRepository;
import com.example.studentapi.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    @Override
    public List<Student> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Student> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Student create(Student student) {
        student.setId(null);
        return repository.save(student);
    }

    @Override
    public Student update(Long id, Student student) {
        Student existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        existing.setFullName(student.getFullName());
        existing.setEmail(student.getEmail());
        existing.setGpa(student.getGpa());

        return repository.save(existing);
    }

    @Override
    public Student patch(Long id, Map<String, Object> updates) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        if (updates.containsKey("fullName")) {
            student.setFullName((String) updates.get("fullName"));
        }
        if (updates.containsKey("email")) {
            student.setEmail((String) updates.get("email"));
        }
        if (updates.containsKey("gpa")) {
            student.setGpa(Double.valueOf(updates.get("gpa").toString()));
        }

        return repository.save(student);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Not found");
        }
        repository.deleteById(id);
    }
}