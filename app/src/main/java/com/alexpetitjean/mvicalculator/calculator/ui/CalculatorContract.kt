package com.alexpetitjean.mvicalculator.calculator.ui

import com.alexpetitjean.mvicalculator.BaseContract
import io.reactivex.Observable

interface CalculatorContract {

    interface View : BaseContract.BaseView<CalculatorViewState> {
        fun numericInputIntent(): Observable<Input.Numeric>
        fun operatorInputIntent(): Observable<Input.Operator>
        fun functionInputIntent(): Observable<Input.Function>
    }

    interface Presenter : BaseContract.BasePresenter

    data class CalculatorViewState(val inputs: List<Input>) {
        companion object {
            val EMPTY = CalculatorViewState(emptyList())
        }

        fun display(): String {
            return inputs.joinToString(separator = "") { input ->
                when (input) {
                    is Input.Numeric -> input.value.toString()
                    is Input.Operator -> " ${input.operator} "
                    else -> "" // TODO: this is janko. functions should not be `Input`s
                }
            }
        }
    }
}
