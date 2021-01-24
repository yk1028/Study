package com.yk1028.api.entity

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors
import javax.persistence.*


@Entity // jpa entity임을 알립니다.
@Table(name = "user") // 'user' 테이블과 매핑됨을 명시
class User(uid: String,
           name: String,
           password: String,
           roles: List<String> = ArrayList(),
           msrl: Long? = null) : UserDetails {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val msrl: Long? = msrl

    @Column(nullable = false, unique = true, length = 30)
    var uid: String = uid
        protected set

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false, length = 100)
    private val password: String = password

    @Column(nullable = false, length = 100)
    var name: String = name
        protected set

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
    override fun getPassword(): String {
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

//
//@Entity
//class User(uid: String, name: String, password: String, msrl: Long? = null) : UserDetails {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    var msrl: Long? = msrl
//
//    @Column(nullable = false, unique = true, length = 30)
//    var uid: String = uid
//
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @Column(nullable = false, length = 100)
//    private var password: String = password
//
//    @Column(nullable = false, length = 100)
//    var name: String = name
//
//    @ElementCollection(fetch = FetchType.EAGER)
//    var roles: List<String> = ArrayList()
//
//    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
//        TODO("Not yet implemented")
//    }
//
//    override fun isEnabled(): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun getUsername(): String {
//        TODO("Not yet implemented")
//    }
//
//    override fun getPassword(): String {
//        TODO("Not yet implemented")
//    }
//
//    override fun isCredentialsNonExpired(): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun isAccountNonExpired(): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun isAccountNonLocked(): Boolean {
//        TODO("Not yet implemented")
//    }
//}