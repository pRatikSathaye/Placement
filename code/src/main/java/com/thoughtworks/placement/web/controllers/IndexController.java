package com.thoughtworks.placement.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class IndexController {

    @RequestMapping(method = RequestMethod.GET, value = {"/", "/index"})
    public ModelAndView index() {
        return new ModelAndView("welcome");
    }
}
