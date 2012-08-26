package com.thoughtworks.placement.web.controllers;

import com.thoughtworks.placement.web.model.Student;
import com.thoughtworks.placement.web.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/student")
public class StudentsController {

    @Autowired
    StudentService studentService;

    @RequestMapping(method = RequestMethod.GET, value = "profile/{SID}")
    public ModelAndView showProfilePage(@PathVariable("SID") String studentID, HttpServletRequest request) {
        Student loggedInUser = (Student) request.getSession().getAttribute(LoginController.LOGGED_IN_USER_KEY);
        if (! loggedInUser.getSID().equals(studentID)){
            return new ModelAndView("redirect:/");
        }
        ModelMap modelMap = new ModelMap();
        modelMap.put("student", studentService.find(studentID));
        return new ModelAndView("student_profile_page", modelMap);
    }
}
