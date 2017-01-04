package com.levelup.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/welcome")
public class WelcomeController {

    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView handleRequestInternal() {
        ModelAndView model = new ModelAndView("welcome");
        model.addObject("msg", "hello world");

        return model;
    }
}
