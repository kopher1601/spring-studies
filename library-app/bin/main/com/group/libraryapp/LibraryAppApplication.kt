package com.group.libraryapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LibraryAppApplication

// static 함수 취급
fun main(args: Array<String>) {
    runApplication<LibraryAppApplication>(*args)
}