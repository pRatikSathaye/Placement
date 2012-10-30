package com.thoughtworks.placement.web.controllers;

import com.thoughtworks.placement.web.model.Event;
import com.thoughtworks.placement.web.model.Role;
import com.thoughtworks.placement.web.model.Student;
import com.thoughtworks.placement.web.services.EventService;
import com.thoughtworks.placement.web.services.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.Mock;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeAvailable;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/placement-servlet-test.xml"})
public class PlacementOfficerControllerTest {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private PlacementOfficerController controller;
    @Mock
    EventService eventService;
    @Mock
    StudentService studentService;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private HandlerAdapter handlerAdapter;
    private MockHttpSession session;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        request = new MockHttpServletRequest();
        request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);

        session = new MockHttpSession();
        session.setAttribute(LoginController.LOGGED_IN_USER_KEY, new Student().setSid("shirish").setRole(Role.PLACEMENT_OFFICER));
        request.setSession(session);

        response = new MockHttpServletResponse();
        handlerAdapter = new AnnotationMethodHandlerAdapter();
    }

    @Test
    public void testList() throws Exception {
        request.setRequestURI("/po/listStudents");
        request.setMethod("GET");
        final ModelAndView responseView = handlerAdapter.handle(request, response, controller);
        assertViewName(responseView, "students_list_page");
        assertModelAttributeAvailable(responseView, "studentList");
        List<Student> studentList = (List<Student>) responseView.getModel().get("studentList");
        assertTrue("There are no students in the database to test list student functionality.", studentList.size()>0);
    }

    @Test
    public void testListWithCriteria() throws Exception {
        request.setRequestURI("/po/listStudents/greater/60");
        request.setMethod("GET");
        final ModelAndView responseView = handlerAdapter.handle(request, response, controller);
        assertViewName(responseView, "students_list_page");
        assertModelAttributeAvailable(responseView, "studentList");
        List<Student> studentList = (List<Student>) responseView.getModel().get("studentList");
        for (Student student : studentList){
            assertTrue("Students marks should be >= specified marks.", student.getMarks().getCurrentDegreeMarks() >= 60);
        }
    }

    @Test
    public void shouldSatisfyCriteria() throws Exception {
        request.setRequestURI("/po/listStudentsByCriteria/60/60/60");
        request.setMethod("GET");
        final ModelAndView responseView = handlerAdapter.handle(request, response, controller);
        assertViewName(responseView, "students_list_page");
        assertModelAttributeAvailable(responseView, "studentList");
        List<Student> studentList = (List<Student>) responseView.getModel().get("studentList");
        for (Student student : studentList){
            assertTrue("Students marks should be >= specified marks.", student.getMarks().getSscMarks() >= 60);
            assertTrue("Students marks should be >= specified marks.", student.getMarks().getHscMarks() >= 60);
            assertTrue("Students marks should be >= specified marks.", student.getMarks().getCurrentDegreeMarks() >= 60);
        }
    }

    @Test
    public void shouldCreateEvent() throws Exception {
        request.setRequestURI("/po/createEvent");
        request.setMethod("GET");
        final ModelAndView responseView = handlerAdapter.handle(request, response, controller);
        assertViewName(responseView, "event_creation_page");
        assertModelAttributeAvailable(responseView, "event");
    }

    @Test
    public void shouldSaveEvent() throws Exception {
        ReflectionTestUtils.setField(controller,"eventService",eventService);
        ReflectionTestUtils.setField(controller,"studentService",studentService);

        Event event = new Event();
        ModelAndView modelAndView = controller.createEvent(event, request);

        verify(eventService).save(event);
        verify(eventService).notifyStudents(event, studentService.getEmailIds(event.getSidsOfEligibleStudents()));
        assertViewName(modelAndView, "event_creation_page");
        assertModelAttributeAvailable(modelAndView, "event");
    }
}
