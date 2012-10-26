package com.thoughtworks.placement.web.controllers;

import com.thoughtworks.placement.web.model.Event;
import com.thoughtworks.placement.web.services.EventService;
import com.thoughtworks.placement.web.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(value = "/po")
public class PlacementOfficerController {

    @Autowired
    StudentService studentService;

    @Autowired
    EventService eventService;

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

    @RequestMapping(method = RequestMethod.GET, value = "listStudentsByCriteria/{sscMarks}/{hscMarks}/{currentDegreeMarks}")
    public ModelAndView listWithCriteria(@PathVariable("sscMarks") double sscMarks, @PathVariable("hscMarks") double hscMarks , @PathVariable("currentDegreeMarks") double currentDegreeMarks) {
        ModelMap modelMap = new ModelMap();
        modelMap.put("studentList", studentService.getAllStudentsWithCriteria(sscMarks,hscMarks,currentDegreeMarks));
        return new ModelAndView("students_list_page", modelMap);
    }


    @RequestMapping(method = RequestMethod.GET, value = "createEvent")
    public ModelAndView eventPage() {
        ModelMap modelMap = new ModelMap();
        modelMap.put("event", new Event());
        return new ModelAndView("event_creation_page", modelMap);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createEvent")
    public ModelAndView createEvent(@ModelAttribute("event") Event event, HttpServletRequest request) {
        ModelMap modelMap = new ModelMap();
        eventService.save(event);
        eventService.notifyStudents(event);
        modelMap.put("event", new Event());
        return new ModelAndView("event_creation_page", modelMap);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
