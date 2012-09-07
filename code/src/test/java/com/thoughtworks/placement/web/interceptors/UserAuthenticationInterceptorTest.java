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

public class UserAuthenticationInterceptorTest {

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockHttpSession session;
    private HandlerAdapter handlerAdapter;

    private UserAuthenticationInterceptor interceptor;

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
    public void testPreHandleForLoggedInUser() throws Exception {
        session.setAttribute(LoginController.LOGGED_IN_USER_KEY, new Student().setSID("shirish").setRole(Role.PLACEMENT_OFFICER));

        interceptor = new UserAuthenticationInterceptor();
        assertTrue("User Interceptor should return true for logged in user", interceptor.preHandle(request, response, handlerAdapter));
    }

    @Test
    public void testPreHandleForGuestUser() throws Exception {
        interceptor = new UserAuthenticationInterceptor();
        assertFalse("User Interceptor should return false for logged in user", interceptor.preHandle(request, response, handlerAdapter));
    }

    @Test
    public void shouldExcludePaths() throws Exception {
        request.setRequestURI("/login");
        request.setMethod("GET");

        interceptor = new UserAuthenticationInterceptor();
        interceptor.setExcludedPaths("login");

        assertTrue("User Interceptor should return true for excluded path for any user", interceptor.preHandle(request, response, handlerAdapter));
    }

    @Test
    public void shouldExcludeExtensions() throws Exception {
        request.setRequestURI("/resources/css/bootstrap.css");
        request.setMethod("GET");

        interceptor = new UserAuthenticationInterceptor();
        interceptor.setExcludedFileExtensions("css");

        assertTrue("User Interceptor should return true for excluded extensions for any user", interceptor.preHandle(request, response, handlerAdapter));
    }

}
