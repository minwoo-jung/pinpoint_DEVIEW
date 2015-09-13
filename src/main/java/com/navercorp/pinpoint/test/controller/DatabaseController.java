package com.navercorp.pinpoint.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.navercorp.pinpoint.test.model.User;
import com.navercorp.pinpoint.test.service.UserService;
import com.navercorp.pinpoint.test.util.Description;

/**
 * @author hyungil.jeong
 */
@Controller
public class DatabaseController {

    @Autowired
    private UserService userService;

    @Description("/getUser?id=deview_1")
    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(@RequestParam(value = "id", defaultValue = "deview_1") String id) {
        return this.userService.get(id);
    }

    @Description("/insertUser?id=deview_1&nickname=Deview User")
    @RequestMapping("/insertUser")
    @ResponseBody
    public String insertUser(
            @RequestParam(value = "id", defaultValue = "deview_1") String id,
            @RequestParam(value = "nickname", defaultValue = "Deview User") String nickName) {
        this.userService.insert(id, nickName);
        return "/insertUser Successful";
    }

    @Description("/deleteUser?id=deview_2")
    @RequestMapping("/deleteUser")
    @ResponseBody
    public String deleteUser(@RequestParam(value = "id", defaultValue = "deview_2") String id) {
        this.userService.delete(id);
        return "/deleteUser Successful";
    }

    @Description("/updateUser?id=deview_1&nickname=Deview User")
    @RequestMapping("/updateUser")
    @ResponseBody
    public String updateUser(
            @RequestParam(value = "id", defaultValue = "deview_1") String id,
            @RequestParam(value = "nickname", defaultValue = "Deview User") String nickName) {
        this.userService.update(id, nickName);
        return "/updateUser Successful";
    }

}
