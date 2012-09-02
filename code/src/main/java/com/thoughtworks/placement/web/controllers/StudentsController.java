package com.thoughtworks.placement.web.controllers;

import com.thoughtworks.placement.web.model.Marks;
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
        modelMap.put("message", request.getParameter("message"));
        return new ModelAndView("student_profile_page", modelMap);
    }

    @RequestMapping(method = RequestMethod.POST, value = "profile/update")
    public ModelAndView registerStudent(@ModelAttribute("student") Student student, HttpServletRequest request) {
        Student loggedInUser = (Student) request.getSession().getAttribute(LoginController.LOGGED_IN_USER_KEY);
        if (! loggedInUser.getSID().equals(student.getSID())){
            return new ModelAndView("redirect:/");
        }

        Student studentToUpdate = studentService.find(student.getSID());
        studentToUpdate.setFullName(student.getFullName());
        studentToUpdate.setPhoneNumber(student.getPhoneNumber());
        studentToUpdate.setEmail(student.getEmail());

        Marks marks = new Marks()
                .setSscMarks(student.getMarks().getSscMarks())
                .setHscMarks(student.getMarks().getHscMarks())
                .setCurrentDegreeMarks(student.getMarks().getCurrentDegreeMarks());

        studentToUpdate.setMarks(marks);

        studentService.save(studentToUpdate);
        request.getSession().setAttribute(LoginController.LOGGED_IN_USER_KEY, student);

        ModelMap modelMap = new ModelMap();
        modelMap.put("message", "Your profile has been updated successfully.");

        return new ModelAndView("redirect:/student/profile/"+student.getSID(), modelMap);
    }
}
