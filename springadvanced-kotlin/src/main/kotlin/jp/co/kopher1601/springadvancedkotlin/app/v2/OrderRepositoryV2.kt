package jp.co.kopher1601.springadvancedkotlin.app.v2

class OrderRepositoryV2{
    fun save(itemId: String) {
        if (itemId == "ex") {
            throw IllegalStateException("예외 발생!")
        }
        sleep(1000)
    }

    private fun sleep(millis: Long) {
        Thread.sleep(millis)
    }
}