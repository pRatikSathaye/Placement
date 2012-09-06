package com.thoughtworks.placement.web.controllers;

import com.thoughtworks.placement.web.model.Student;
import com.thoughtworks.placement.web.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import java.util.Map;

import static org.junit.Assert.*;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeAvailable;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/placement-servlet.xml"})
public class LoginControllerTest {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private LoginController controller;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private HandlerAdapter handlerAdapter;
    private MockHttpSession session;

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
    public void testLogin() throws Exception {
        request.setRequestURI("/login");
        request.setMethod("GET");
        final ModelAndView responseView = handlerAdapter.handle(request, response, controller);
        assertViewName(responseView, "login_page");

        assertModelAttributeAvailable(responseView, "user");
    }

    @Test
    public void testDoLogin() throws Exception {
        User user = new User();
        user.setUsername("shirish"); user.setPassword("shirish");
        final ModelAndView responseView = controller.doLogin(user, request);
        assertViewName(responseView, "redirect:/");
        assertNotNull(request.getSession().getAttribute(LoginController.LOGGED_IN_USER_KEY));
    }

    @Test
    public void testDoLoginInvalidUserNameOrPassword() throws Exception {
        User user = new User();
        user.setUsername("shirish_invalid"); user.setPassword("shirish");
        final ModelAndView responseView = controller.doLogin(user, request);
        assertViewName(responseView, "login_page");
        assertNull(request.getSession().getAttribute(LoginController.LOGGED_IN_USER_KEY));
        assertModelAttributeAvailable(responseView, "user");
        assertModelAttributeAvailable(responseView, "errorMessage");
        Map<String, Object> model = responseView.getModel();
        assertEquals("Error message is different.", model.get("errorMessage"), "Invalid username or password");
    }

    @Test
    public void testLogout() throws Exception {
        request.setRequestURI("/logout");
        request.setMethod("POST");
        session.setAttribute(LoginController.LOGGED_IN_USER_KEY, new Student().setSID("shirish"));

        final ModelAndView responseView = handlerAdapter.handle(request, response, controller);

        assertViewName(responseView, "redirect:/");
        assertNull("Logged in user object is still present in session.", session.getAttribute(LoginController.LOGGED_IN_USER_KEY));
        assertTrue("Session is still valid after logout.", session.isInvalid());
    }
}
