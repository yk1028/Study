package com.yk1028.api.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HelloController {

    @GetMapping("/helloworld/string")
    @ResponseBody
    fun helloworldString() = "helloworld"

    @GetMapping("/helloworld/json")
    @ResponseBody
    fun helloworldJson(): Message {
        return Message("helloworld")
    }

    @GetMapping("/helloworld/page")
    fun helloworld(): String = "helloworld"

    data class Message(val message: String)
}