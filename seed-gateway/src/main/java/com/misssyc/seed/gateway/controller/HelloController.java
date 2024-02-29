package com.misssyc.seed.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李生平
 * @since 2024/2/5
 **/
@RestController
@RequestMapping("/api-gateway")
public class HelloController {

    @GetMapping("/hello")
    public String hello(String name) {
        return "hello: " + name;
    }
}
