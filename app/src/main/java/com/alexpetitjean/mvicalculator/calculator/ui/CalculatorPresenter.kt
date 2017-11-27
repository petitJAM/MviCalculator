package com.alexpetitjean.mvicalculator.calculator.ui

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class CalculatorPresenter(private val view: CalculatorContract.View)
    : CalculatorContract.Presenter {

    private val disposables = CompositeDisposable()

    init {
        view.presenter = this
    }

    override fun subscribe() {
        Observable.merge(listOf(view.numericInputIntent(), view.operatorInputIntent(), view.functionInputIntent()))
                .doOnNext { Timber.d("Input: $it") }
                .scan(CalculatorViewState.EMPTY, { previousState: CalculatorViewState, newInput: Input ->
                    // TODO: this is biz logic
                    CalculatorViewState(previousState.inputs.plus(newInput))
                })
                .startWith(CalculatorViewState.EMPTY)
                .doOnNext { Timber.d("ViewState: $it") }
                .doOnSubscribe { disposables += it}
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { viewState ->
                            view.render(viewState)
                        },
                        onError = { throwable ->
                            throwable.printStackTrace()
                        })
    }

    override fun unsubscribe() {
        disposables.clear()
    }
}
