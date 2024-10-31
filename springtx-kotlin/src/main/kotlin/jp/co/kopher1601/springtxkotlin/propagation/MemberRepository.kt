package jp.co.kopher1601.springtxkotlin.propagation

import jakarta.persistence.EntityManager
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class MemberRepository(
    private val em: EntityManager,
) {

    val log = LoggerFactory.getLogger(this::class.java)

    // JPA 의 모든 데이터의 변경은 @Transactional 내에서 이루어져야 한다.
    @Transactional
    fun save(member: Member) {
        log.info("member 저장")
        em.persist(member)
    }

    fun find(username: String): Member? {
        return em.createQuery("select m from Member m where m.username = :username", Member::class.java)
            .setParameter("username", username)
            .resultList
            .firstOrNull()
    }

}