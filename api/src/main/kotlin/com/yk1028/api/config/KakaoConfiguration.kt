package com.yk1028.api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class KakaoConfiguration {
    @Bean
    fun getRestTemplate(): RestTemplate {
        return RestTemplate()
    }
}