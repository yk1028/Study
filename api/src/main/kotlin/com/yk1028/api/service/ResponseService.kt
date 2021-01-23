package com.yk1028.api.service

import com.yk1028.api.model.reponse.CommonResult
import com.yk1028.api.model.reponse.ListResult
import com.yk1028.api.model.reponse.SingleResult
import org.springframework.stereotype.Service

@Service // 해당 Class가 Service임을 명시합니다.
class ResponseService {
    // enum으로 api 요청 결과에 대한 code, message를 정의합니다.
    enum class CommonResponse(var code: Int, var msg: String) {
        SUCCESS(0, "성공하였습니디.")
    }

    fun <T> getSingleResult(data: T): SingleResult<T> {
        return SingleResult(data, successResult())
    }

    fun <T> getListResult(list: List<T>): ListResult<T> {
        return ListResult(list, successResult())
    }

    // 결과 모델에 api 요청 성공 데이터를 세팅해주는 메소드
    // 성공 결과만 처리하는 메소드
    fun successResult(): CommonResult = CommonResult(true, CommonResponse.SUCCESS.code, CommonResponse.SUCCESS.msg)

    // 실패 결과만 처리하는 메소드
    fun failResult(code: Int, msg: String): CommonResult = CommonResult(false, code, msg)

}