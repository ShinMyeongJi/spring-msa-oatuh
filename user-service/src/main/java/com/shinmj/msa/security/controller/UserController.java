package com.shinmj.msa.security.controller;

import com.shinmj.msa.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName :  com.shinmj.msa.security.controller
 * fileName : UserController
 * author :  home
 * date : 22. 9. 12.
 * description :
 */
@RequestMapping("/auth")
@RestController
public class UserController {


    @Autowired
    UserService userService;

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/etc")
    public UserDetails etc() {
        return userService.loadUserByUsername("test");
    }
}
