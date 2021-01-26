package com.yk1028.api.controller.common

import com.yk1028.api.property.SocialProperty
import com.yk1028.api.service.user.KakaoService
import org.springframework.core.env.Environment
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/social/login")
class SocialController(private val env: Environment,
                       private val kakaoService: KakaoService) {

    val baseUrl: String = SocialProperty.baseUrl
    val kakaoClientId: String = SocialProperty.kakaoClientId
    val kakaoRedirect: String = SocialProperty.kakaoRedirect

    /**
     * 카카오 로그인 페이지
     */
    @GetMapping
    fun socialLogin(mav: ModelAndView): ModelAndView {
        val loginUrl: StringBuilder = StringBuilder()
                .append("https://kauth.kakao.com/oauth/authorize")
                .append("?client_id=").append(kakaoClientId)
                .append("&response_type=code")
                .append("&redirect_uri=").append(baseUrl).append(kakaoRedirect)
        mav.addObject("loginUrl", loginUrl)
        mav.viewName = "social/login"
        return mav
    }

    /**
     * 카카오 인증 완료 후 리다이렉트 화면
     */
    @GetMapping(value = ["/kakao"])
    fun redirectKakao(mav: ModelAndView, @RequestParam code: String?): ModelAndView {
        mav.addObject("authInfo", kakaoService.getKakaoTokenInfo(code))
        mav.viewName = "social/redirectKakao"
        return mav
    }
}