package com.yk1028.api.entity

import javax.persistence.*

@Entity
class User(uid: String, name: String) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var msrl: Long? = null
    @Column(nullable = false, unique = true, length = 30)
    var uid: String = uid
    @Column(nullable = false, length = 100)
    var name: String = name
}