package com.deepak.ml.controller;

import com.deepak.ml.model.User;
import com.deepak.ml.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.security.RolesAllowed;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600,allowedHeaders = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    UserRepository userRepository;

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/")
    public List<User> users() {

        return userRepository.findAll();

    }

}