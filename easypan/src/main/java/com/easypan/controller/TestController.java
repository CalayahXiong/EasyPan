package com.easypan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //controller + response body
public class TestController {
    
    @RequestMapping("/test") //localhost:7090/api/test
    public String test(){
        return "test";
    }
}
