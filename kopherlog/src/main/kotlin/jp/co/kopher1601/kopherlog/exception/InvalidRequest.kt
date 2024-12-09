package jp.co.kopher1601.kopherlog.exception

class InvalidRequest(
    fieldName: String? = null,
    message: String? = "잘못된 요청입니다.",
) : KopherlogException(message) {
    override fun statusCode(): Int {
        return 400
    }

    init {
        addValidation(fieldName, message)
    }
}
