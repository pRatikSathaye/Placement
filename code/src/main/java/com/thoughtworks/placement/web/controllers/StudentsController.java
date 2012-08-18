package com.thoughtworks.placement.web.controllers;

import com.thoughtworks.placement.web.model.DummyStudent;
import com.thoughtworks.placement.web.model.Student;
import com.thoughtworks.placement.web.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/student")
public class StudentsController {

    @Autowired
    StudentService studentService;

    @RequestMapping(method = RequestMethod.GET, value = "register")
    public ModelAndView showRegisterForm() {
        ModelMap modelMap = new ModelMap();
        modelMap.put("student", new DummyStudent());
        return new ModelAndView("student_registration_page", modelMap);
    }

    @RequestMapping(method = RequestMethod.POST, value = "register")
    public ModelAndView registerStudent(@ModelAttribute("student") Student student) {
        studentService.save(student);
        return new ModelAndView("redirect:profile/"+student.getSID());
    }

    @RequestMapping(method = RequestMethod.GET, value = "profile/{SID}")
    public ModelAndView showProfilePage(@PathVariable("SID") String studentID) {
        ModelMap modelMap = new ModelMap();
        modelMap.put("student", studentService.find(studentID));
        return new ModelAndView("student_profile_page", modelMap);
    }

    @RequestMapping(method = RequestMethod.GET, value = "list")
    public ModelAndView list() {
        ModelMap modelMap = new ModelMap();
        modelMap.put("studentList", studentService.getAll());
        return new ModelAndView("students_list_page", modelMap);
    }
}
