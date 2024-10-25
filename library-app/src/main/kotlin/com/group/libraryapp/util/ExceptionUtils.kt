package com.group.libraryapp.util

import org.springframework.data.repository.CrudRepository

fun fail(): Nothing {
    throw IllegalArgumentException()
}

fun <T, ID> CrudRepository<T, ID>.findByIdOrThrow(id: ID): T {
    return this.findByIdOrThrow(id) ?: fail()
}