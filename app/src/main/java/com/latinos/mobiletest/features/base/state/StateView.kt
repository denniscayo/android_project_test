package com.latinos.mobiletest.features.base.state

/*
* The states of a view
*/
sealed class StateView {
    object Idle : StateView()
    data class Loading(val showLoading: Boolean = true) : StateView()
    data class Error(val showLoading: Boolean = true) : StateView()
    data class Success(val showLoading: Boolean = true) : StateView()
}