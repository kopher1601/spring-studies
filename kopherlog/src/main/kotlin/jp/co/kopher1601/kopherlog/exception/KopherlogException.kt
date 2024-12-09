package jp.co.kopher1601.kopherlog.exception

abstract class KopherlogException(message: String?) : RuntimeException(message) {

    private val _validation = mutableMapOf<String?, String?>()
    val validation: MutableMap<String?, String?>
        get() = _validation


    abstract fun statusCode(): Int

    fun addValidation(fieldName: String?, message: String?) {
        validation[fieldName] = message
    }
}
