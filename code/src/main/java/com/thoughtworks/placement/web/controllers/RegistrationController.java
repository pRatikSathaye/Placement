package com.thoughtworks.placement.web.controllers;

import com.thoughtworks.placement.web.model.DummyStudent;
import com.thoughtworks.placement.web.model.Student;
import com.thoughtworks.placement.web.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class RegistrationController {

    @Autowired
    StudentService studentService;

    @RequestMapping(method = RequestMethod.GET, value = "register")
    public ModelAndView showRegisterForm() {
        ModelMap modelMap = new ModelMap();
        modelMap.put("student", new DummyStudent());
        return new ModelAndView("student_registration_page", modelMap);
    }

    @RequestMapping(method = RequestMethod.POST, value = "register")
    public ModelAndView registerStudent(@ModelAttribute("student") Student student, HttpServletRequest request) {
        studentService.save(student);
        request.getSession().setAttribute(LoginController.LOGGED_IN_USER_KEY, student);
        return new ModelAndView("redirect:student/profile/"+student.getSID());
    }

}
