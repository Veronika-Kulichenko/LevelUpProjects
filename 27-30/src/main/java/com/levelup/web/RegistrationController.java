package com.levelup.web;

import com.levelup.core.entity.User;
import com.levelup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.GET;

@Controller
@RequestMapping(value = "/register")
public class RegistrationController {

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView getUserRegistrationPage() throws Exception {
        return new ModelAndView("registration");
    }

    @RequestMapping(method = RequestMethod.POST)
    protected ResponseEntity<HttpStatus> registerUser(@RequestBody User user) throws Exception {
        if (userService.registerUser(user)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getUserByEmail", method = RequestMethod.GET)
    public User getUserByEmail(@RequestBody String email) {
        User user = userService.getUserByEmail(email);
        System.out.println(user);
        return user;
    }



}
