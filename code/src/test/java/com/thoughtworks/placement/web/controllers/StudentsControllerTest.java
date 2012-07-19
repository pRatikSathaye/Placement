package com.thoughtworks.placement.web.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import static org.springframework.test.web.ModelAndViewAssert.assertViewName;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/placement-servlet.xml"})
public class StudentsControllerTest {

    @Autowired
    private ApplicationContext applicationContext;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private HandlerAdapter handlerAdapter;
    private StudentsController controller;

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        handlerAdapter = new AnnotationMethodHandlerAdapter();
        controller = new StudentsController();
    }

    @Test
    public void testRegister() throws Exception {
        request.setRequestURI("/student/register");
        request.setMethod("GET");
        request.setAttribute (HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
        //System.out.println("Supports? " + handlerAdapter.supports(controller));
        final ModelAndView responseView = handlerAdapter.handle(request, response, controller);
        assertViewName(responseView, "registration");
    }

    @Test
    public void testShowPage() throws Exception {
        request.setRequestURI("/student/list");
        request.setMethod("GET");
        request.setAttribute (HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
        //System.out.println("Supports? " + handlerAdapter.supports(controller));
        final ModelAndView responseView = handlerAdapter.handle(request, response, controller);
        assertViewName(responseView, "studentsList");
    }
}