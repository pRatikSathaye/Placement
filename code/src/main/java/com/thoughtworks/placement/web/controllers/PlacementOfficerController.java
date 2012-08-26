package com.thoughtworks.placement.web.controllers;

import com.thoughtworks.placement.web.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/po")
public class PlacementOfficerController {

    @Autowired
    StudentService studentService;

    @RequestMapping(method = RequestMethod.GET, value = "listStudents")
    public ModelAndView list() {
        ModelMap modelMap = new ModelMap();
        modelMap.put("studentList", studentService.getAll());
        return new ModelAndView("students_list_page", modelMap);
    }

    @RequestMapping(method = RequestMethod.GET, value = "listStudents/greater/{currentDegreeMarks}")
    public ModelAndView listWithCriteria(@PathVariable("currentDegreeMarks") double currentDegreeMarks) {
        ModelMap modelMap = new ModelMap();
        modelMap.put("studentList", studentService.getAllCurrentMarksGreaterThan(currentDegreeMarks));
        return new ModelAndView("students_list_page", modelMap);
    }
}
