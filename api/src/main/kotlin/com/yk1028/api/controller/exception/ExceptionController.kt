package com.yk1028.api.controller.exception

import com.yk1028.api.advice.exception.CAuthenticationEntryPointException
import com.yk1028.api.model.reponse.CommonResult
import org.springframework.security.access.AccessDeniedException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


// import 생략
@RestController
@RequestMapping(value = ["/exception"])
class ExceptionController {
    @GetMapping(value = ["/entrypoint"])
    fun entrypointException(): CommonResult {
        throw CAuthenticationEntryPointException()
    }

    @GetMapping(value = ["/accessdenied"])
    fun accessdeniedException(): CommonResult? {
        throw AccessDeniedException("")
    }
}