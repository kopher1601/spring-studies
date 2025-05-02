package kuke.board.common.snowflake

class Snowflake {
    private val nodeId: Long = java.util.random.RandomGenerator.getDefault().nextLong(maxNodeId + 1)

    // UTC = 2024-01-01T00:00:00Z
    private val startTimeMillis: Long = 1704067200000L

    private var lastTimeMillis: Long = startTimeMillis
    private var sequence: Long = 0L

    @kotlin.jvm.Synchronized
    fun nextId(): Long {
        var currentTimeMillis: Long = java.lang.System.currentTimeMillis()

        check(currentTimeMillis >= lastTimeMillis) { "Invalid Time" }

        if (currentTimeMillis == lastTimeMillis) {
            sequence = (sequence + 1) and maxSequence
            if (sequence == 0L) {
                currentTimeMillis = waitNextMillis(currentTimeMillis)
            }
        } else {
            sequence = 0
        }

        lastTimeMillis = currentTimeMillis

        return (((currentTimeMillis - startTimeMillis) shl (NODE_ID_BITS + SEQUENCE_BITS))
                or (nodeId shl SEQUENCE_BITS)
                or sequence)
    }

    private fun waitNextMillis(currentTimestamp: Long): Long {
        var currentTimestamp: Long = currentTimestamp
        while (currentTimestamp <= lastTimeMillis) {
            currentTimestamp = java.lang.System.currentTimeMillis()
        }
        return currentTimestamp
    }

    companion object {
        private const val UNUSED_BITS: Int = 1
        private const val EPOCH_BITS: Int = 41
        private const val NODE_ID_BITS: Int = 10
        private const val SEQUENCE_BITS: Int = 12

        private val maxNodeId: Long = (1L shl NODE_ID_BITS) - 1
        private val maxSequence: Long = (1L shl SEQUENCE_BITS) - 1
    }
}