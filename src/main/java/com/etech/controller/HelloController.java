package com.etech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Arrays;

@RestController
public class HelloController {
    @Autowired
    RequestMappingHandlerMapping mapping;
    @RequestMapping("/")
    public String hello() {

        return Arrays.toString(mapping.getHandlerMethods().keySet().toArray());
    }
}
