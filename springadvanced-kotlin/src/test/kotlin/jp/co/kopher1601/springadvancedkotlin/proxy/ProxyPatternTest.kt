package jp.co.kopher1601.springadvancedkotlin.proxy

import jp.co.kopher1601.springadvancedkotlin.proxy.code.CacheProxy
import jp.co.kopher1601.springadvancedkotlin.proxy.code.ProxyPatternClient
import jp.co.kopher1601.springadvancedkotlin.proxy.code.RealSubject
import org.junit.jupiter.api.Test

class ProxyPatternTest {

    @Test
    fun noProxyTest() {
        val realSubject = RealSubject()
        val client = ProxyPatternClient(realSubject)
        client.execute()
        client.execute()
        client.execute()
    }

    @Test
    fun cacheProxyTest() {
        val realSubject = RealSubject()
        val cacheProxy = CacheProxy(realSubject)
        val client = ProxyPatternClient(cacheProxy)
        client.execute()
        client.execute()
        client.execute()
    }
}