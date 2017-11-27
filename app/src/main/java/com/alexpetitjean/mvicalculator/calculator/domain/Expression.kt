package com.alexpetitjean.mvicalculator.calculator.domain

sealed class Expression {

    abstract fun compute(): Int
    abstract fun display(): String

    data class SimpleExpression(private val value: Int) : Expression() {
        override fun compute(): Int {
            return value
        }

        override fun display(): String {
            return value.toString()
        }
    }

    data class ComplexExpression(private val lhs: Expression,
                                 private val rhs: Expression,
                                 private val operator: Operator)
        : Expression() {

        override fun compute(): Int {
            return operator.operate(lhs, rhs)
        }

        override fun display(): String {
            return "${lhs.display()} ${operator.display()} ${rhs.display()}"
        }
    }
}
