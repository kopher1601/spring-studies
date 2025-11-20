package jp.kopher.springexposed.repository

import jp.kopher.springexposed.domain.Member
import jp.kopher.springexposed.domain.Members
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
class MemberRepository {

    fun save(member: Member): ResultRow {
        val id = Members.insert {
            it[name] = member.name
            it[age] = member.age
        } get Members.id
        return Members.selectAll().where { Members.id eq id }.single()
    }

}