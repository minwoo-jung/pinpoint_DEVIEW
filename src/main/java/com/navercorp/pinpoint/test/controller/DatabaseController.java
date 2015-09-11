package com.navercorp.pinpoint.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.navercorp.pinpoint.test.service.UserService;

/**
 * @author hyungil.jeong
 */
@Controller
public class DatabaseController {

    @Autowired
    private UserService userService;
    
    @RequestMapping("/getUser")
    @ResponseBody
    public String getUser() {
        this.userService.get();
        return "SUCCESS";
    }

}
