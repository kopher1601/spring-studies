package hellojpa

import jakarta.persistence.Persistence

class JpaMain

fun main(args: Array<String>) {
    val emf = Persistence.createEntityManagerFactory("hello")
    val em = emf.createEntityManager()

    em.transaction.begin()
    try {
        // 비영속
        val member = Member("HelloJPA", 100L)

        // 영속
        println("==== BEFORE ====")
        em.persist(member)
        println("==== AFTER ====")

        em.transaction.commit()
    } catch (e: Exception) {
        em.transaction.rollback()
    } finally {
        em.close()
    }
    emf.close()
}