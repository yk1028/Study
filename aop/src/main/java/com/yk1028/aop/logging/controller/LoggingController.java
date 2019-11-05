package com.yk1028.aop.logging.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoggingController {
    private static final Logger log = LoggerFactory.getLogger(LoggingController.class);

    @GetMapping("/hello")
    public String hello() {
        log.info("hello");
        return "hello.html";
    }
}
