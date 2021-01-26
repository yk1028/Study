package com.yk1028.api.entity

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.spi.CalendarDataProvider
import java.util.stream.Collectors
import javax.persistence.*


@Entity // jpa entity임을 알립니다.
@Table(name = "user") // 'user' 테이블과 매핑됨을 명시
class User(uid: String,
           name: String,
           password: String? = null,
           provider: String? = null,
           roles: List<String> = ArrayList(),
           msrl: Long? = null) : UserDetails {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val msrl: Long? = msrl

    @Column(nullable = false, unique = true, length = 30)
    var uid: String = uid
        protected set

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(length = 100)
    private val password: String? = password

    @Column(nullable = false, length = 100)
    var name: String = name
        protected set

    @Column(length = 100)
    var provider: String? = provider

    @ElementCollection(fetch = FetchType.EAGER)
    val roles: List<String> = roles

    fun updateUser(uid: String, name: String) {
        this.uid = uid
        this.name = name
    }

    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return roles.stream().map { role: String? -> SimpleGrantedAuthority(role) }.collect(Collectors.toList())
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Json data로 출력하지 않을 데이터
    override fun getUsername(): String {
        return uid
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    override fun getPassword(): String? {
        return password
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    override fun isAccountNonExpired(): Boolean {
        return true
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    override fun isAccountNonLocked(): Boolean {
        return true
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    override fun isEnabled(): Boolean {
        return true
    }
}