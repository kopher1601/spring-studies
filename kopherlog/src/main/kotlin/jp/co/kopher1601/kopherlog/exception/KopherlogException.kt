package jp.co.kopher1601.kopherlog.exception

abstract class KopherlogException(message: String?) : RuntimeException(message) {

    abstract fun statusCode(): Int

}
