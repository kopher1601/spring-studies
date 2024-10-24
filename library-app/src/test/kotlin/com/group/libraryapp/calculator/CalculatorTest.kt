package com.group.libraryapp.calculator

fun main() {
    val test = CalculatorTest()
    test.addTest()
}

class CalculatorTest {
    fun addTest() {
        val calculator = Calculator(5)
        calculator.add(3)

        val expectedCalculator = Calculator(8)
        if (calculator != expectedCalculator) {
            throw IllegalArgumentException("Something went wrong")
        }

    }
}