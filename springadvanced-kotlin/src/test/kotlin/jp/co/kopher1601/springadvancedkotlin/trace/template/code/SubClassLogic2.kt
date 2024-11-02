package jp.co.kopher1601.springadvancedkotlin.trace.template.code

class SubClassLogic2: AbstractTemplate() {

    override fun call() {
        log.info("비즈니스 로직2 실행")
    }
}