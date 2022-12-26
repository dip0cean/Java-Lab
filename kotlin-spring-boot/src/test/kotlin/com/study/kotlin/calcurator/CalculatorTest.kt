package com.study.kotlin.calcurator

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CalculatorTest {

    @Test
    @DisplayName("덧셈")
    fun add() {
        // given
        val calculator = Calculator(5)

        // when
        calculator.add(3)

        // then
        if (calculator.number != 8) {
            throw IllegalStateException()
        }
    }

    @Test
    @DisplayName("뺄셈")
    fun subtract() {
        // given
        val calculator = Calculator(5)

        // when
        calculator.subtract(3)

        // then
        if (calculator.number != 2) {
            throw IllegalStateException()
        }
    }

    @Test
    @DisplayName("곱셈")
    fun multiply() {
    }

    @Test
    @DisplayName("나눗셈")
    fun divide() {
    }
}