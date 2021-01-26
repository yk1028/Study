package com.yk1028.api.advice.exception

class CUserNotFoundException(msg: String? = "유저를 찾을 수 없습니다.") : RuntimeException(msg)