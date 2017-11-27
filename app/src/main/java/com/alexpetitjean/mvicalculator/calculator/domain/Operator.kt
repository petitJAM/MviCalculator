package com.alexpetitjean.mvicalculator.calculator.domain

class Operator(private val op: String) {

    class UnknownOperatorError(message: String) : Error(message)

    fun operate(left: Expression, right: Expression): Int {
        return when(op) {
            "+" -> add(left, right)
            "-" -> subtract(left, right)
            "*" -> multiply(left, right)
//            "/" -> divide(left, right)
            else -> throw UnknownOperatorError("Unknown operator: $op")
        }
    }

    fun display(): String {
        return op
    }

    private fun add(left: Expression, right: Expression): Int {
        return left.compute() + right.compute()
    }

    private fun subtract(left: Expression, right: Expression): Int {
        return left.compute() - right.compute()
    }

    private fun multiply(left: Expression, right: Expression): Int {
        return left.compute() * right.compute()
    }

    private fun divide(left: Expression, right: Expression): Int {
        return left.compute() / right.compute()
    }
}
