package jp.co.kopher1601.springadvancedkotlin.proxy.code

class ProxyPatternClient(
    private val subject: Subject,
) {

    fun execute() {
        subject.operation()
    }

}