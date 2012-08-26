package com.thoughtworks.placement.web.interceptors;

import com.thoughtworks.placement.web.controllers.LoginController;
import com.thoughtworks.placement.web.model.Role;
import com.thoughtworks.placement.web.model.Student;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PlacementOfficerAuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        Student user = (Student) request.getSession().getAttribute(LoginController.LOGGED_IN_USER_KEY);

        if (user.getRole() == Role.PLACEMENT_OFFICER) {
            return true;
        }
        response.sendRedirect(request.getContextPath()+"/");
        return false;
    }
}
