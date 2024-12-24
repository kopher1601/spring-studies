package jp.kopher.customfailureanalyzer.exception


class UrlNotAccessibleException(
    private val _url: String,
) : RuntimeException() {

    val url: String
        get() = _url
}
