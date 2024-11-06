package jp.co.kopher1601.springadvancedkotlin.proxy.code

import org.slf4j.LoggerFactory

class CacheProxy(
    private val target: Subject,
    private var cacheValue: String? = null,
) : Subject {

    private val log = LoggerFactory.getLogger(CacheProxy::class.java)

    override fun operation(): String {
        log.info("프록시 호출")
        if (cacheValue == null) {
            cacheValue = target.operation()
        }
        return cacheValue!!
    }
}