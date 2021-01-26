package com.yk1028.api.model.social

class KakaoProfile {
    var id: Long? = null
    var properties: Properties? = null

    class Properties {
        var nickname: String? = null
        var thumbnail_image: String? = null
        var profile_image: String? = null
    }
}