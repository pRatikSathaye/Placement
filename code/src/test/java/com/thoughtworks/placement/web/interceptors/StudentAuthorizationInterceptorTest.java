package com.thoughtworks.placement.web.interceptors;

import com.thoughtworks.placement.web.controllers.LoginController;
import com.thoughtworks.placement.web.model.Role;
import com.thoughtworks.placement.web.model.Student;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StudentAuthorizationInterceptorTest {

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockHttpSession session;
    private HandlerAdapter handlerAdapter;

    private StudentAuthorizationInterceptor interceptor;

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);

        session = new MockHttpSession();
        request.setSession(session);

        response = new MockHttpServletResponse();
        handlerAdapter = new AnnotationMethodHandlerAdapter();
    }

    @Test
    public void testPreHandleForPlacementOfficer() throws Exception {
        session.setAttribute(LoginController.LOGGED_IN_USER_KEY, new Student().setSid("shirish").setRole(Role.PLACEMENT_OFFICER));

        interceptor = new StudentAuthorizationInterceptor();
        assertFalse("Student Interceptor should return false for Placement officer", interceptor.preHandle(request, response, handlerAdapter));
    }

    @Test
    public void testPreHandleForStudent() throws Exception {
        session.setAttribute(LoginController.LOGGED_IN_USER_KEY, new Student().setSid("shirish").setRole(Role.STUDENT));

        interceptor = new StudentAuthorizationInterceptor();
        assertTrue("Student Interceptor should return true for Student", interceptor.preHandle(request, response, handlerAdapter));
    }
}
