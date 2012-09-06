package com.thoughtworks.placement.web.controllers;

import com.thoughtworks.placement.web.model.Student;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeAvailable;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/placement-servlet.xml"})
public class StudentsControllerTest {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private StudentsController controller;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private HandlerAdapter handlerAdapter;
    private MockHttpSession session;


    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);

        session = new MockHttpSession();
        session.setAttribute(LoginController.LOGGED_IN_USER_KEY, new Student().setSID("shirish"));
        request.setSession(session);

        response = new MockHttpServletResponse();
        handlerAdapter = new AnnotationMethodHandlerAdapter();
    }


    @Test
    public void testViewProfile() throws Exception {
        request.setRequestURI("/student/profile/shirish");
        request.setMethod("GET");
        final ModelAndView responseView = handlerAdapter.handle(request, response, controller);
        assertViewName(responseView, "student_profile_page");
        assertModelAttributeAvailable(responseView, "student");
        assertModelAttributeAvailable(responseView, "message");
    }

    @Test
    public void testViewProfileForOtherUser() throws Exception {
        request.setRequestURI("/student/profile/1");
        request.setMethod("GET");
        final ModelAndView responseView = handlerAdapter.handle(request, response, controller);
        assertViewName(responseView, "redirect:/");
    }


    @Test @Ignore
    public void testUpdateProfile() throws Exception {
        fail("Not implemented");
    }
}
