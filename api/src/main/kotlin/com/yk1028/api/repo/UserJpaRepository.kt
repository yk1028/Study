package com.yk1028.api.repo

import com.yk1028.api.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserJpaRepository : JpaRepository<User, Long> {
    fun findByUid(email: String?): Optional<User>
}