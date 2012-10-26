package com.thoughtworks.placement.web.services;

import com.thoughtworks.placement.web.model.Role;
import com.thoughtworks.placement.web.model.Student;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/placement-servlet-test.xml"})
public class StudentServiceTest {

    @Autowired
    private StudentService service;

    @Test
    public void testFindByUserId() throws Exception {
        Student student = service.find("shirish");
        assertNotNull(student);
        assertEquals("Username should be same", "shirish", student.getSID());
    }

    @Test
    public void testGetAllStudents() throws Exception {
        List<Student> students = (List<Student>) service.getAll();
        assertNotNull(students);
        for (Student student : students){
            assertEquals("Role should be student", Role.STUDENT, student.getRole());
        }
    }

    @Test
    public void testGetAllCurrentMarksGreaterThan() throws Exception {
        List<Student> students = (List<Student>) service.getAllCurrentMarksGreaterThan(60);
        assertNotNull(students);
        for (Student student : students){
            assertTrue("Students marks should be >= specified marks.", student.getMarks().getCurrentDegreeMarks() >= 60);
        }
    }

    @Test
    public void testGetAllStudentsWithCriteria() throws Exception {
        List<Student> students = service.getAllStudentsWithCriteria(60,60,60);
        assertNotNull(students);
        for (Student student : students){
            assertTrue("Students marks should be >= specified marks.", student.getMarks().getSscMarks() >= 60);
            assertTrue("Students marks should be >= specified marks.", student.getMarks().getHscMarks() >= 60);
            assertTrue("Students marks should be >= specified marks.", student.getMarks().getCurrentDegreeMarks() >= 60);
        }
    }

    @Test
    public void shouldReturnListOfEmailIds(){
        List<String> emailIds = service.getEmailIds("gurpreet,test");
        assertEquals(2,emailIds.size());
    }

    @Test @Ignore
    public void testSaveStudent() throws Exception {
        fail("Not implemented");
    }

}
