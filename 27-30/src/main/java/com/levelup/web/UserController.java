package com.levelup.web;

import com.levelup.core.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView handleRequestInternal() {
        return new ModelAndView("user");
    }

    @RequestMapping(method = RequestMethod.POST)
    protected void doSomething(@RequestParam(value = "email") String email,
                               @RequestParam(value = "pass") String pass) {
        System.out.println("Email: " + email + ", password: " + pass);
    }

    @RequestMapping(value = "/firstStepRegistration", method = RequestMethod.POST)
    protected ResponseEntity<HttpStatus> firstStepRegistration (@RequestBody User user){
        System.out.println(user);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
}
