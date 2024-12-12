package jp.co.kopher1601.kopherlog.exception

class Unauthorized(
    message: String? = "Not authorized",
) : KopherlogException(message) {
    override fun statusCode(): Int {
        return 404
    }
}
