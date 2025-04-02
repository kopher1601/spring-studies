package hellojpa

import jakarta.persistence.Persistence

class JpaMain

fun main(args: Array<String>) {
    val emf = Persistence.createEntityManagerFactory("hello")
    val em = emf.createEntityManager()

    em.transaction.begin()
    try {
        val members = em.createQuery("select m from Member m", Member::class.java).resultList

        em.transaction.commit()
    } catch (e: Exception) {
        em.transaction.rollback()
    } finally {
        em.close()
    }
    emf.close()
}