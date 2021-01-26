package com.yk1028.api.controller.v1

import com.yk1028.api.advice.exception.CEmailSigninFailedException
import com.yk1028.api.advice.exception.CUserExistException
import com.yk1028.api.advice.exception.CUserNotFoundException
import com.yk1028.api.config.security.JwtTokenProvider
import com.yk1028.api.entity.User
import com.yk1028.api.model.reponse.CommonResult
import com.yk1028.api.model.reponse.SingleResult
import com.yk1028.api.model.social.KakaoProfile
import com.yk1028.api.repo.UserJpaRepository
import com.yk1028.api.service.ResponseService
import com.yk1028.api.service.user.KakaoService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.util.*


@Api(tags = ["1. Sign"])
@RestController
@RequestMapping(value = ["/v1"])
class SignController(private val userJpaRepo: UserJpaRepository,
                     private val jwtTokenProvider: JwtTokenProvider,
                     private val responseService: ResponseService,
                     private val passwordEncoder: PasswordEncoder,
                     private val kakaoService: KakaoService) {

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

        userJpaRepo.save(User(
                id,
                name,
                passwordEncoder.encode(password),
                null,
                Collections.singletonList("ROLE_USER")))

        return responseService.successResult()
    }

    @ApiOperation(value = "소셜 로그인", notes = "소셜 회원 로그인을 한다.")
    @PostMapping(value = ["/signin/{provider}"])
    fun signinByProvider(
            @ApiParam(value = "서비스 제공자 provider", required = true, defaultValue = "kakao") @PathVariable provider: String,
            @ApiParam(value = "소셜 access_token", required = true) @RequestParam accessToken: String): SingleResult<String>? {
        val profile: KakaoProfile = kakaoService.getKakaoProfile(accessToken)
        val user = userJpaRepo.findByUidAndProvider(profile.id.toString(), provider).orElseThrow { CUserNotFoundException() }
        return responseService.getSingleResult(jwtTokenProvider.createToken(java.lang.String.valueOf(user.msrl), user.roles))
    }

    @ApiOperation(value = "소셜 계정 가입", notes = "소셜 계정 회원가입을 한다.")
    @PostMapping(value = ["/signup/{provider}"])
    fun signupProvider(@ApiParam(value = "서비스 제공자 provider", required = true, defaultValue = "kakao") @PathVariable provider: String,
                       @ApiParam(value = "소셜 access_token", required = true) @RequestParam accessToken: String,
                       @ApiParam(value = "이름", required = true) @RequestParam name: String): CommonResult? {
        val profile = kakaoService.getKakaoProfile(accessToken)
        val user = userJpaRepo.findByUidAndProvider(profile.id.toString(), provider)
        if (user.isPresent) throw CUserExistException()
        userJpaRepo.save(
                User(profile.id.toString(), name, null, provider, Collections.singletonList("ROLE_USER")))
        return responseService.successResult()
    }
}