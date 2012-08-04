package com.thoughtworks.placement.web.controllers;


import com.thoughtworks.placement.web.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "/student")
public class StudentsController {
    Student currentUnsafeStudent = null;

    @RequestMapping(method = RequestMethod.GET, value = "register")
    public ModelAndView showRegisterForm() {
        return new ModelAndView("student_registration_page", "student", new Student());
    }

    @RequestMapping(method = RequestMethod.POST, value = "register")
    public String registerStudent(@ModelAttribute("student") Student student) {
        //TODO: Need to store student to mongo!
        currentUnsafeStudent = student;
        return "redirect:profile/"+student.getSID();
    }

    @RequestMapping(method = RequestMethod.GET, value = "profile/{SID}")
    public ModelAndView showProfilePage(@PathVariable("SID") String studentID) {
        //TODO: Need to read profile from Mongo!
        ModelMap modelMap = new ModelMap();
        modelMap.put("student", currentUnsafeStudent);
        return new ModelAndView("student_profile_page", modelMap);
    }

    @RequestMapping(method = RequestMethod.GET, value = "list")
    public ModelAndView list() {
        //TODO: Need to read list of all students from Mongo!
        ModelMap modelMap = new ModelMap();
        modelMap.put("studentList", getHardCodedStudentList());
        return new ModelAndView("students_list_page", modelMap);
    }

    private List<Student> getHardCodedStudentList() {
        List<Student> list = new ArrayList<Student>();

        Student student = new Student();
        student.setSID("1")
                .setFullName("Shirish Padalkar")
                .setEmail("shirish4you@gmail.com")
                .setPhoneNumber("9876543210");

        list.add(student);

        student = new Student();
        student.setSID("2")
                .setFullName("Gurpreet Luthra")
                .setEmail("gsluthra@gmail.com")
                .setPhoneNumber("9376543210");

        list.add(student);
        return list;
    }

}
