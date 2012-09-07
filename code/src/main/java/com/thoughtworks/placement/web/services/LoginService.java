package com.thoughtworks.placement.web.services;

import com.thoughtworks.placement.web.dao.StudentRepository;
import com.thoughtworks.placement.web.model.Student;
import com.thoughtworks.placement.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    StudentRepository repository;

    public Student checkIfValidUser(User user){
        if (user == null){
            return null;
        }

        Student student = repository.findOne(user.getUsername());

        if (student!=null && student.getPassword().equals(user.getPassword().trim())){
            return student;
        }

        return null;
    }
}
