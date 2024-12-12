package jp.co.kopher1601.kopherlog.config

import jp.co.kopher1601.kopherlog.config.data.UserSession
import org.slf4j.LoggerFactory
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class AuthResolver: HandlerMethodArgumentResolver {

    val log = LoggerFactory.getLogger(this::class.java)

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType == UserSession::class.java
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any {
        return UserSession("코퍼")
    }
}
