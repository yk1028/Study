package com.yk1028.api.service.user

import com.google.gson.Gson
import com.yk1028.api.advice.exception.CCommunicationException
import com.yk1028.api.model.social.KakaoProfile
import com.yk1028.api.model.social.RetKakaoAuth
import org.springframework.core.env.Environment
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate

@Service
class KakaoService(private val restTemplate: RestTemplate, private val env: Environment, private val gson: Gson) {

    val baseUrl: String = com.yk1028.api.property.SocialProperty.baseUrl
    val kakaoClientId: String = com.yk1028.api.property.SocialProperty.kakaoClientId
    val kakaoRedirect: String = com.yk1028.api.property.SocialProperty.kakaoRedirect

    fun getKakaoProfile(accessToken: String): KakaoProfile {
        // Set header : Content-type: application/x-www-form-urlencoded
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_FORM_URLENCODED
        headers["Authorization"] = "Bearer $accessToken"

        // Set http entity
        val request = HttpEntity<MultiValueMap<String, String>?>(null, headers)
        try {
            // Request profile
            val response = restTemplate.postForEntity("https://kapi.kakao.com/v2/user/me", request, String::class.java)
            if (response.statusCode == HttpStatus.OK) return gson.fromJson(response.body, KakaoProfile::class.java)
        } catch (e: Exception) {
            throw CCommunicationException()
        }
        throw CCommunicationException()
    }

    fun getKakaoTokenInfo(code: String?): RetKakaoAuth? {
        // Set header : Content-type: application/x-www-form-urlencoded
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_FORM_URLENCODED
        // Set parameter
        val params: MultiValueMap<String, String> = LinkedMultiValueMap()
        params.add("grant_type", "authorization_code")
        params.add("client_id", kakaoClientId)
        params.add("redirect_uri", baseUrl + kakaoRedirect)
        params.add("code", code)
        // Set http entity
        val request = HttpEntity(params, headers)
        val response = restTemplate.postForEntity("https://kauth.kakao.com/oauth/token", request, String::class.java)
        println("응답 : $response")
        return if (response.statusCode == HttpStatus.OK) {
            gson.fromJson(response.body, RetKakaoAuth::class.java)
        } else null
    }

}