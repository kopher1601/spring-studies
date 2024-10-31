package jp.co.kopher1601.springtxkotlin.propagation

import jakarta.persistence.EntityManager
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Repository
class LogRepository @Autowired constructor(
    private val em: EntityManager,
) {

    val log = LoggerFactory.getLogger(this::class.java)

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun save(logMessage: Log) {
        log.info("log 저장")
        em.persist(logMessage)

        if (logMessage.contains("로그예외")) {
            log.info("log 저장시 예외 발생")
            throw RuntimeException("예외 발생") // rollback
        }
    }

    fun find(message: String): Log? {
        return em.createQuery("select l from Log l where l.message = :message", Log::class.java)
            .setParameter("message", message)
            .resultList
            .firstOrNull()
    }
}