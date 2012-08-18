package com.thoughtworks.placement.web.services;

import com.thoughtworks.placement.web.dao.StudentRepository;
import com.thoughtworks.placement.web.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository repository;

    public Student save(Student student) {
        repository.save(student);
        return student;
    }

    public Student find(String studentID) {
        return repository.findOne(studentID);
    }

    public Iterable<Student> getAll() {
        return repository.findAll();
    }
}
