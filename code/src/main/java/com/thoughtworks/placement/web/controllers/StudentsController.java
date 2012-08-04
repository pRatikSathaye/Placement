package com.thoughtworks.placement.web.controllers;


import com.thoughtworks.placement.web.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "/student")
public class StudentsController {

    @RequestMapping(method = RequestMethod.GET, value = "register")
    public ModelAndView register() {
        return new ModelAndView("registerStudent");
    }

    @RequestMapping(method = RequestMethod.POST, value = "register")
    public ModelAndView doRegister() {
        return new ModelAndView("registrationSuccessful");
    }

    @RequestMapping(method = RequestMethod.GET, value = "list")
    public ModelAndView list() {
        List<Student> list = new ArrayList<Student>();

        Student student = new Student("1");
        student.setFullName("Shirish Padalkar")
                .setEmail("shirish4you@gmail.com")
                .setPhoneNumber("9876543210");

        list.add(student);

        student = new Student("2");
        student.setFullName("Gurpreet Luthra")
                .setEmail("gsluthra@gmail.com")
                .setPhoneNumber("9376543210");

        list.add(student);

        ModelMap modelMap = new ModelMap();
        modelMap.put("studentList", list);

        return new ModelAndView("studentsList", modelMap);
    }

}
