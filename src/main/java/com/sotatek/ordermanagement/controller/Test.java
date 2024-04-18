package com.sotatek.ordermanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "test")
public class Test {
    @GetMapping(path = "test1")
    public String test1() {
        return "test1";
    }
}
