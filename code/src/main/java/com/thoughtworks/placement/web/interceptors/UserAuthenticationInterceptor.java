package com.thoughtworks.placement.web.interceptors;

import com.thoughtworks.placement.web.controllers.LoginController;
import com.thoughtworks.placement.web.model.Student;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class UserAuthenticationInterceptor extends HandlerInterceptorAdapter {

    List<String> excludedPaths;
    List<String> excludedFileExtensions;

    public UserAuthenticationInterceptor(){
        excludedPaths = new ArrayList<String>();
        excludedFileExtensions = new ArrayList<String>();
    }

    public void setExcludedPaths(String excludedPaths) {
        this.excludedPaths = Arrays.asList(excludedPaths.split(","));
    }

    public void setExcludedFileExtensions(String excludedFileExtensions) {
        this.excludedFileExtensions = Arrays.asList(excludedFileExtensions.split(","));
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String uri = request.getRequestURI();

        if (shouldBeIntercepted(uri)) {
            Student userData = (Student) request.getSession().getAttribute(LoginController.LOGGED_IN_USER_KEY);
            if (userData == null) {
                response.sendRedirect(request.getContextPath()+"/login");
                return false;
            }
        }
        return true;
    }

    private boolean shouldBeIntercepted(String uri) {
        for(String extension : excludedFileExtensions){
            if (uri.endsWith("."+extension.trim())){
                return false;
            }
        }
        for (String path : excludedPaths){
            if (uri.endsWith(path.trim())){
                return false;
            }
        }

        return true;
    }
}
