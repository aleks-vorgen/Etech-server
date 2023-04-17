package com.etech.controller;

import com.etech.model.User;
import com.etech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //@GetMapping("/users")
    public List<User> getUserList() {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
}
