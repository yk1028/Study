package com.yk1028.api.service

import com.yk1028.api.advice.exception.CUserNotFoundException
import com.yk1028.api.repo.UserJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service


@Service
class CustomUserDetailService(private val userJpaRepo: UserJpaRepository) : UserDetailsService {

    override fun loadUserByUsername(userPk: String): UserDetails {
        return userJpaRepo.findByIdOrNull(java.lang.Long.valueOf(userPk)) ?: throw CUserNotFoundException()
    }
}