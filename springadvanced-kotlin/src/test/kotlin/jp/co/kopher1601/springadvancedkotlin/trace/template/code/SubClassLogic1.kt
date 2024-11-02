package jp.co.kopher1601.springadvancedkotlin.trace.template.code

class SubClassLogic1: AbstractTemplate() {

    override fun call() {
        log.info("비즈니스 로직1 실행")
    }
}