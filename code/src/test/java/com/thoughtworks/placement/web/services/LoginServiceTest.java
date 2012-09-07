package com.thoughtworks.placement.web.services;

import com.thoughtworks.placement.web.model.Student;
import com.thoughtworks.placement.web.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/placement-servlet.xml"})
public class LoginServiceTest {

    @Autowired
    private LoginService service;

    @Test
    public void shouldReturnStudentIfValidUsername() throws Exception {
        User user = new User();
        user.setUsername("shirish"); user.setPassword("shirish");

        Student student = service.checkIfValidUser(user);
        assertNotNull(student);
        assertEquals("Username should be equal", user.getUsername(), student.getSID());
        assertEquals("Password should be equal", user.getPassword(), student.getPassword());
    }

    @Test
    public void shouldReturnNullIfInvalidUsername() throws Exception {
        User user = new User();
        user.setUsername("shirish_invalid"); user.setPassword("shirish");

        Student student = service.checkIfValidUser(user);
        assertNull(student);
    }

    @Test
    public void shouldReturnNullIfInvalidPassword() throws Exception {
        User user = new User();
        user.setUsername("shirish"); user.setPassword("shirish_invalid");

        Student student = service.checkIfValidUser(user);
        assertNull(student);
    }

    @Test
    public void shouldReturnNullIfNullUserIsPassed() throws Exception {
        Student student = service.checkIfValidUser(null);
        assertNull(student);
    }
}
