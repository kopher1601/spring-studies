package com.group.libraryapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LibraryAppApplication

// Top line 에 함수를 만들경우 static 함수로 간주된다.
fun main(args: Array<String>) {
    // 자바의 가변인수 연산자 ... 은 코틀린에서는 * spread 연산자로 대체
    runApplication<LibraryAppApplication>(*args)
}