package com.thoughtworks.placement.web.controllers;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value = "/students")
public class StudentsController {

    private static final Log logger = getLogger();

    private static Log getLogger() {
        try {
            return LogFactory.getLog(StudentsController.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "list")
    public ModelAndView showPage() {
        logger.info("Returning products page view");

        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "Shirish");
        map.put("age", "24");

        return new ModelAndView("studentsList.jsp", map);
    }

}
