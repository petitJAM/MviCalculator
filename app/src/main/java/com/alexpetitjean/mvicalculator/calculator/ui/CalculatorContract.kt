package com.alexpetitjean.mvicalculator.calculator.ui

import com.alexpetitjean.mvicalculator.BaseContract
import io.reactivex.Observable

interface CalculatorContract {

    interface View : BaseContract.BaseView<Presenter, CalculatorViewState> {
        fun numericInputIntent(): Observable<Input.Numeric>
        fun operatorInputIntent(): Observable<Input.Operator>
        fun functionInputIntent(): Observable<Input.Function>
    }

    interface Presenter : BaseContract.BasePresenter

}

data class CalculatorViewState(val inputs: List<Input>) {

    companion object {
        val EMPTY = CalculatorViewState(emptyList())
    }
}
