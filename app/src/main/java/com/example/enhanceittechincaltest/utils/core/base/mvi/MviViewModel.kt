/*
 * Copyright (C) 2022, amansatija
 * All rights reserved.
 */
package com.example.enhanceittechincaltest.utils.core.base.mvi

import com.example.enhanceittechincaltest.utils.core.base.mvvm.MvvmViewModel

import com.example.enhanceittechincaltest.utils.core.flow.MutableEventFlow
import com.example.enhanceittechincaltest.utils.core.flow.asEventFlow

abstract class MviViewModel<STATE, EVENT> : MvvmViewModel() {

    private val _stateFlow = MutableEventFlow<STATE>()
    val stateFlow = _stateFlow.asEventFlow()

    abstract fun onTriggerEvent(eventType: EVENT)

    protected fun setState(state: STATE) = safeLaunch {
        _stateFlow.emit(state)
    }
}
