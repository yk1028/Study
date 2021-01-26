package com.yk1028.api.model.social

class RetKakaoAuth(
        val access_token: String? = null,
        val token_type: String? = null,
        val refresh_token: String? = null,
        val expires_in: Long = 0,
        val scope: String? = null
)