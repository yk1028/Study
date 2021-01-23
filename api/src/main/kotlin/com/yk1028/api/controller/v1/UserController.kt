package com.yk1028.api.controller.v1

import com.yk1028.api.entity.User
import com.yk1028.api.repo.UserJpaRepository
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.web.bind.annotation.*


@Api(tags = ["1. User"])
@RestController // 결과값을 JSON으로 출력합니다.
@RequestMapping(value = ["/v1"])
class UserController(private val userJpaRepository: UserJpaRepository) {

    @ApiOperation(value = "회원 조회", notes = "모든 회원을 조회한다")
    @GetMapping(value = ["/user"])
    fun findAllUser(): List<User> {
        return userJpaRepository.findAll()
    }

    @ApiOperation(value = "회원 입력", notes = "회원을 입한다")
    @PostMapping(value = ["/user"])
    fun save(@ApiParam(value = "회원 아이디", required = true) @RequestParam uid: String,
             @ApiParam(value = "회원 이름", required = true) @RequestParam name: String): User {
        return userJpaRepository.save(User(uid, name))
    }
}