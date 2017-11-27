package com.alexpetitjean.mvicalculator

interface BaseContract {

    interface BaseView<in ViewState> {
        fun render(viewState: ViewState)
    }

    interface BasePresenter
}
