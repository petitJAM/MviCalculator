package com.alexpetitjean.mvicalculator.calculator.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alexpetitjean.mvicalculator.R
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_calculator.*

class CalculatorActivity
    : AppCompatActivity(),
      CalculatorContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        CalculatorPresenter(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.subscribe()
    }

    override fun onStop() {
        super.onStop()
        presenter.unsubscribe()
    }

    override lateinit var presenter: CalculatorContract.Presenter

    override fun render(viewState: CalculatorViewState) {
        expressionDisplay.text = viewState.inputs.joinToString(separator = "") { input ->
            when (input) {
                is Input.Numeric -> input.value.toString()
                is Input.Operator -> input.operator
                is Input.Function -> " ${input.function} " // TODO: this is janko. functions should not be `Input`s
                // Maybe it's not jank?
            }
        }
    }

    override fun numericInputIntent(): Observable<Input.Numeric> {
        val input0 = RxView.clicks(numericInput0).map { Input.Numeric(0) }
        val input1 = RxView.clicks(numericInput1).map { Input.Numeric(1) }
        val input2 = RxView.clicks(numericInput2).map { Input.Numeric(2) }
        val input3 = RxView.clicks(numericInput3).map { Input.Numeric(3) }
        val input4 = RxView.clicks(numericInput4).map { Input.Numeric(4) }
        val input5 = RxView.clicks(numericInput5).map { Input.Numeric(5) }
        val input6 = RxView.clicks(numericInput6).map { Input.Numeric(6) }
        val input7 = RxView.clicks(numericInput7).map { Input.Numeric(7) }
        val input8 = RxView.clicks(numericInput8).map { Input.Numeric(8) }
        val input9 = RxView.clicks(numericInput9).map { Input.Numeric(9) }
        return Observable.merge(listOf(input0, input1, input2, input3, input4, input5, input6, input7, input8, input9))
    }

    override fun operatorInputIntent(): Observable<Input.Operator> {
        val inputAdd = RxView.clicks(operatorInputAdd).map { Input.Operator.ADD }
        val inputSubtract = RxView.clicks(operatorInputSubtract).map { Input.Operator.SUBTRACT }
        val inputMultiply = RxView.clicks(operatorInputMultiply).map { Input.Operator.MULTIPLY }
        val inputDivide = RxView.clicks(operatorInputDivide).map { Input.Operator.DIVIDE }
        return Observable.merge(listOf(inputAdd, inputSubtract, inputMultiply, inputDivide))
    }

    override fun functionInputIntent(): Observable<Input.Function> {
        val inputEquals = RxView.clicks(functionInputEquals).map { Input.Function.EQUALS }
        val inputDelete = RxView.clicks(functionInputDelete).map { Input.Function.DELETE }
        val inputClear = RxView.clicks(functionInputClear).map { Input.Function.CLEAR }
        return Observable.merge(listOf(inputEquals, inputDelete, inputClear))
    }
}
