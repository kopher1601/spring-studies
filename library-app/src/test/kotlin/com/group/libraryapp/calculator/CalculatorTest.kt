package com.group.libraryapp.calculator

fun main() {
    val test = CalculatorTest()
    test.addTest()
}

class CalculatorTest {
    fun addTest() {
        // given
        val calculator = Calculator(5)

        // when
        calculator.add(3)

        // then
        if (calculator.number != 8) {
            throw IllegalArgumentException("Something went wrong")
        }

    }
}