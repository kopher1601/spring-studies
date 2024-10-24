package com.group.libraryapp.calculator

fun main() {
    val test = CalculatorTest()
    test.addTest()
}

class CalculatorTest {
    fun addTest() {
        val calculator = Calculator(5)
        calculator.add(3)

        if (calculator.number != 8) {
            throw IllegalArgumentException("Something went wrong")
        }

    }
}