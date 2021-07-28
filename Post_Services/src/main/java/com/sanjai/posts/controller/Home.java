package com.sanjai.posts.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {


    @RequestMapping(method = RequestMethod.GET,value = "/")
    public String home() {
        return "Hello World!";
    }
}
