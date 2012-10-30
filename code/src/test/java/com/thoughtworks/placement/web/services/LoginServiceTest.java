package com.thoughtworks.placement.web.services;

import com.thoughtworks.placement.web.model.Role;
import com.thoughtworks.placement.web.model.Student;
import com.thoughtworks.placement.web.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/placement-servlet-test.xml"})
public class LoginServiceTest {

    @Autowired
    private LoginService service;

    @Test
    public void shouldReturnStudentIfValidUsername() throws Exception {
        User user = new User();
        user.setUsername("shirish"); user.setPassword("shirish");

        Student student = service.checkIfValidUser(user);
        assertNotNull(student);
        assertEquals("Username should be equal", user.getUsername(), student.getSid());
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
    public void shouldReturnAdminUser() throws Exception {
        User user = new User();
        user.setUsername("admin"); user.setPassword("password");

        Student student = service.checkIfValidUser(user);
        assertNotNull(student);
        assertEquals(Role.PLACEMENT_OFFICER,student.getRole());
    }

    @Test
    public void shouldReturnNullIfInvalidAdminUser() throws Exception {
        User user = new User();
        user.setUsername("admin_invalid"); user.setPassword("password");

        Student student = service.checkIfValidUser(user);
        assertNull(student);
    }


    @Test
    public void shouldReturnNullIfNullUserIsPassed() throws Exception {
        Student student = service.checkIfValidUser(null);
        assertNull(student);
    }
}
