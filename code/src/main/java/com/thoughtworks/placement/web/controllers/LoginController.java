package com.thoughtworks.placement.web.controllers;

import com.thoughtworks.placement.web.model.Student;
import com.thoughtworks.placement.web.model.User;
import com.thoughtworks.placement.web.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/")
public class LoginController {

    @Autowired
    LoginService loginService;
    public static final String LOGGED_IN_USER_KEY = "LOGGED_IN_USER";

    @RequestMapping(method = RequestMethod.GET, value = "login")
    public ModelAndView login(){
        ModelMap modelMap = new ModelMap();
        modelMap.put("user", new User());
        return new ModelAndView("login_page", modelMap);
    }

    @RequestMapping(method = RequestMethod.POST, value = "login")
    public ModelAndView doLogin(@ModelAttribute("user") User user, HttpServletRequest request) {
        Student loggedInUser = loginService.doLogin(user);

        if (loggedInUser!=null){
            request.getSession().setAttribute(LOGGED_IN_USER_KEY, loggedInUser);
            return new ModelAndView("redirect:/");
        }

        ModelMap modelMap = new ModelMap();
        modelMap.put("user", user);
        modelMap.put("errorMessage", "Invalid username or password");

        return new ModelAndView("login_page", modelMap);
    }

    @RequestMapping(method = RequestMethod.POST, value = "logout")
    public ModelAndView doLogin(HttpServletRequest request) {
        request.getSession().removeAttribute(LOGGED_IN_USER_KEY);
        request.getSession().invalidate();
        return new ModelAndView("redirect:/");
    }
}
