package jp.kopher.springexposed.domain

import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.datetime.CurrentDateTime
import org.jetbrains.exposed.v1.datetime.datetime


object Members: Table("members") {
    val id = long("id").autoIncrement()
    val name = varchar("name", 255)
    val age = integer("age")
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    override val primaryKey = PrimaryKey(id)
}

data class Member(
    val id: Long? = null,
    val name: String,
    val age: Int,
    val createdAt: LocalDateTime? = null,
)