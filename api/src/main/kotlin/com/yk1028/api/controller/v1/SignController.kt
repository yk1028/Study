package com.yk1028.api.controller.v1

import com.yk1028.api.advice.exception.CEmailSigninFailedException
import com.yk1028.api.config.security.JwtTokenProvider
import com.yk1028.api.entity.User
import com.yk1028.api.model.reponse.CommonResult
import com.yk1028.api.model.reponse.SingleResult
import com.yk1028.api.repo.UserJpaRepository
import com.yk1028.api.service.ResponseService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*


@Api(tags = ["1. Sign"])
@RestController
@RequestMapping(value = ["/v1"])
class SignController(private val userJpaRepo: UserJpaRepository,
                     private val jwtTokenProvider: JwtTokenProvider,
                     private val responseService: ResponseService,
                     private val passwordEncoder: PasswordEncoder) {

    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @PostMapping(value = ["/signin"])
    fun signin(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam id: String,
               @ApiParam(value = "비밀번호", required = true) @RequestParam password: String): SingleResult<String> {

        val user: User = userJpaRepo.findByUid(id).orElseThrow { CEmailSigninFailedException() }

        if (!passwordEncoder.matches(password, user.password)) throw CEmailSigninFailedException()

        return responseService.getSingleResult(jwtTokenProvider.createToken(java.lang.String.valueOf(user.msrl), user.roles))
    }

    @ApiOperation(value = "가입", notes = "회원가입을 한다.")
    @PostMapping(value = ["/signup"])
    fun signin(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam id: String,
               @ApiParam(value = "비밀번호", required = true) @RequestParam password: String,
               @ApiParam(value = "이름", required = true) @RequestParam name: String): CommonResult {
        
        userJpaRepo.save(User(id, name, passwordEncoder.encode(password), Collections.singletonList("ROLE_USER")))

        return responseService.successResult()
    }
}