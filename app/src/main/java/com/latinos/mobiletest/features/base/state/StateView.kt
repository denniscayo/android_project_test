package com.latinos.mobiletest.features.base.state

/*
* The states of a fragment
*/
sealed class StateView {
    object Idle : StateView()
    data class Loading(val showLoading: Boolean = true) : StateView()
    object EmptyView : StateView()
    object Error : StateView()
    object Success : StateView()
}