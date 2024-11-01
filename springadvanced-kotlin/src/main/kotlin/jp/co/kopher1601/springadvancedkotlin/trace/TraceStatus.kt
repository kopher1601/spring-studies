package jp.co.kopher1601.springadvancedkotlin.trace

class TraceStatus(
    val traceId: TraceId,
    val  startTimeMs: Long,
    val message: String,
) {

}