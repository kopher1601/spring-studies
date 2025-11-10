package jp.kopher.springsecurityinaction.model

class Employee(
    val name: String,
    val books: List<String> = mutableListOf(),
    val roles: List<String> = mutableListOf(),
)