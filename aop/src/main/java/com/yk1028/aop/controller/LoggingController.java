package com.yk1028.aop.controller;

import com.yk1028.aop.aspect.timer.Timer;
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

    @GetMapping("/hello2")
    @Timer
    public String hello2() {
        log.info("hello2");
        return "hello.html";
    }
}
