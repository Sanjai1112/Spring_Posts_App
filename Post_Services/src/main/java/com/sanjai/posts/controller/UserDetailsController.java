package com.sanjai.posts.controller;

import com.sanjai.posts.entity.UserDetails;
import com.sanjai.posts.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserDetailsController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET,value = "/users")
    public List<UserDetails> getUserDetails(){
        return userRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/company")
    public List<UserDetails> findAllUsersByCompanyName(@RequestParam(name = "companyName",defaultValue = "Google") String companyName){
        System.out.println(companyName);
        return userRepository.findAllUsersByCompanyName(companyName);
    }
}
