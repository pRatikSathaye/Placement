package com.thoughtworks.placement.web.services;

import com.thoughtworks.placement.web.dao.StudentRepository;
import com.thoughtworks.placement.web.model.Role;
import com.thoughtworks.placement.web.model.Student;
import com.thoughtworks.placement.web.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Properties;

@Service
public class LoginService {

    @Autowired
    StudentRepository repository;
    private static Logger LOGGER = Logger.getLogger(LoginService.class);
    public void setRepository(StudentRepository repository) {
        this.repository = repository;
    }

    public Student checkIfValidUser(User user){
        if (user == null){
            return null;
        }
        Student student = checkIfAdmin(user);
        if (student != null) {
            return student;
        }

        student = repository.findOne(user.getUsername());

        if (student!=null && student.getPassword().equals(user.getPassword().trim())){
            return student;
        }

        return null;
    }

    private Student checkIfAdmin(User user) {
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream("credentials.properties"));
            if ((properties.getProperty("username").equals(user.getUsername())) && (properties.getProperty("password").equals(user.getPassword()))) {
                return new Student("1", "Admin", "", "password", "", null, Role.PLACEMENT_OFFICER);
            }
        } catch (Exception e) {
            LOGGER.info("Could not find credential.properties: " + e);
        }
        return null;
    }
}
