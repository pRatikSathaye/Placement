package com.thoughtworks.placement.web.interceptors;

import com.thoughtworks.placement.web.model.Student;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Component
public class UserAuthenticationInterceptor extends HandlerInterceptorAdapter {

    List<String> exclude;

    List<String> excludeExtensions;

    public void setExclude(String exclude) {
        this.exclude = Arrays.asList(exclude.split(","));
    }

    public void setExcludeExtensions(String excludeExtensions) {
        this.excludeExtensions = Arrays.asList(excludeExtensions.split(","));
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String uri = request.getRequestURI();

        if (shouldBeIntercepted(uri)) {
            Student userData = (Student) request.getSession().getAttribute("LOGGEDIN_USER");
            if (userData == null) {
                response.sendRedirect(request.getContextPath()+"/login");
                return false;
            }
        }
        return true;
    }

    private boolean shouldBeIntercepted(String uri) {
        for(String extension : excludeExtensions){
            if (uri.endsWith("."+extension)){
                return false;
            }
        }
        for (String endpoint : exclude){
            if (uri.endsWith(endpoint)){
                return false;
            }
        }

        return true;
    }
}
