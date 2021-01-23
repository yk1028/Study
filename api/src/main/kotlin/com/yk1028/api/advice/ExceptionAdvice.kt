package com.yk1028.api.advice

import com.yk1028.api.advice.exception.CUserNotFoundException
import com.yk1028.api.model.reponse.CommonResult
import com.yk1028.api.service.ResponseService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest


@RestControllerAdvice
class ExceptionAdvice(private val responseService: ResponseService) {

    @ExceptionHandler(CUserNotFoundException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected fun userNotFoundException(request: HttpServletRequest?, e: CUserNotFoundException?): CommonResult {
        return responseService.failResult
    }
}