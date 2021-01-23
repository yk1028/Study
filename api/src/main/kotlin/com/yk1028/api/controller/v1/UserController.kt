package com.yk1028.api.controller.v1

import com.yk1028.api.entity.User
import com.yk1028.api.repo.UserJpaRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController // 결과값을 JSON으로 출력합니다.
@RequestMapping(value = ["/v1"])
class UserController(private val userJpaRepository: UserJpaRepository) {

    @GetMapping(value = ["/user"])
    fun findAllUser(): List<User> {
        return userJpaRepository.findAll()
    }

    @PostMapping(value = ["/user"])
    fun save(): User {
        val user: User = User("yumi@naver.com", "유미")
        return userJpaRepository.save(user)
    }
}