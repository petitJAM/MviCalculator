package com.alexpetitjean.mvicalculator.calculator.ui

sealed class Input {

    data class Numeric(val value: Int) : Input()

    data class Operator(val operator: String) : Input() {
        companion object {
            val ADD = Operator("+")
            val SUBTRACT = Operator("-")
            val MULTIPLY = Operator("*")
            val DIVIDE = Operator("/")
        }
    }

    data class Function(val function: String) : Input() {
        companion object {
            val EQUALS = Function("equals")
            val DELETE = Function("delete")
            val CLEAR = Function("clear")
        }
    }
}
