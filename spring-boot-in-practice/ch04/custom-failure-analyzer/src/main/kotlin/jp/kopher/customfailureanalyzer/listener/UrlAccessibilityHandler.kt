package jp.kopher.customfailureanalyzer.listener


import jp.kopher.customfailureanalyzer.exception.UrlNotAccessibleException
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class UrlAccessibilityHandler(
    @Value("\${api.url:https://dog.ceo/}")
    private val url: String
) {

    @EventListener(classes = [ContextRefreshedEvent::class])
    fun listen() {
        throw UrlNotAccessibleException(url)
    }
}
