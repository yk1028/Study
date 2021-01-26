package com.yk1028.api.model.reponse

import io.swagger.annotations.ApiModelProperty

open class CommonResult(
        @ApiModelProperty(value = "응답 성공여부 true/false") val success: Boolean,
        @ApiModelProperty(value = "응답 코드번호  : >= 0 정상, < 0 비정상") val code: Int,
        @ApiModelProperty(value = "응답 메세지") val msg: String
)