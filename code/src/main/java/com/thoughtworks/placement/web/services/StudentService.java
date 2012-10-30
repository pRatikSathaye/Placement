package com.thoughtworks.placement.web.services;

import com.thoughtworks.placement.web.dao.StudentRepository;
import com.thoughtworks.placement.web.model.Role;
import com.thoughtworks.placement.web.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository repository;

    public void setRepository(StudentRepository repository) {
        this.repository = repository;
    }

    public Student save(Student student) {
        return repository.save(student);
    }

    public Student find(String studentID) {
        return repository.findOne(studentID);
    }

    public Iterable<Student> getAll() {
        return repository.findByRole(Role.STUDENT);
    }

    public Iterable<Student> getAllCurrentMarksGreaterThan(double marks) {
        return repository.findByCurrentDegreeMarksGreaterThan(marks);
    }

    public List<Student> getAllStudentsWithCriteria(double sscMarks, double hscMarks, double currentDegreeMarks) {
        return repository.findBySscMarksAndHscMarksAndCurrentDegreeMarksGreaterThan(sscMarks, hscMarks, currentDegreeMarks);
    }

    public List<String> getEmailIds(String studentIds) {
        List<String> emailIds=new ArrayList<String>();
        List<Student> students = repository.findBySidIn(Arrays.asList(studentIds.split(",")));
        for (Student student : students) {
            emailIds.add(student.getEmail());
        }
        return emailIds;
    }
}
