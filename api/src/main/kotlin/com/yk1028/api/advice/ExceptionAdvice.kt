package com.yk1028.api.advice

import com.yk1028.api.advice.exception.CAuthenticationEntryPointException
import com.yk1028.api.advice.exception.CEmailSigninFailedException
import com.yk1028.api.advice.exception.CUserNotFoundException
import com.yk1028.api.model.reponse.CommonResult
import com.yk1028.api.service.ResponseService
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest


@RestControllerAdvice
class ExceptionAdvice(private val responseService: ResponseService, private val messageSource: MessageSource) {

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected fun defaultException(request: HttpServletRequest?, e: Exception?): CommonResult {
        // 예외 처리의 메시지를 MessageSource에서 가져오도록 수정
        return responseService.failResult(
                Integer.valueOf(getMessage("unKnown.code")),
                getMessage("unKnown.msg"))
    }

    @ExceptionHandler(CUserNotFoundException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected fun userNotFoundException(request: HttpServletRequest?, e: CUserNotFoundException?): CommonResult {
        // 예외 처리의 메시지를 MessageSource에서 가져오도록 수정
        return responseService.failResult(
                Integer.valueOf(getMessage("userNotFound.code")),
                getMessage("userNotFound.msg"))
    }

    // code정보에 해당하는 메시지를 조회합니다.
    private fun getMessage(code: String): String = getMessage(code, null)

    // code정보, 추가 argument로 현재 locale에 맞는 메시지를 조회합니다.
    private fun getMessage(code: String, args: Array<Any>?): String =
            messageSource.getMessage(code, args, LocaleContextHolder.getLocale())

    @ExceptionHandler(CEmailSigninFailedException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected fun emailSigninFailed(request: HttpServletRequest, e: CEmailSigninFailedException): CommonResult {
        return responseService.failResult(
                Integer.valueOf(getMessage("emailSigninFailed.code")),
                getMessage("emailSigninFailed.msg"))
    }

    @ExceptionHandler(CAuthenticationEntryPointException::class)
    fun authenticationEntryPointException(request: HttpServletRequest?, e: CAuthenticationEntryPointException?): CommonResult? {
        return responseService.failResult(
                Integer.valueOf(getMessage("entryPointException.code")),
                getMessage("entryPointException.msg"))
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun AccessDeniedException(request: HttpServletRequest?, e: AccessDeniedException?): CommonResult? {
        return responseService.failResult(
                Integer.valueOf(getMessage("accessDenied.code")),
                getMessage("accessDenied.msg"))
    }
}