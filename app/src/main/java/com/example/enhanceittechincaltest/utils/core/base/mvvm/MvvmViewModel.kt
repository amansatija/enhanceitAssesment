/*
 * Copyright (C) 2022, amansatija
 * All rights reserved.
 */
package com.example.enhanceittechincaltest.utils.core.base.mvvm

import androidx.lifecycle.*
import com.example.enhanceittechincaltest.utils.core.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

open class MvvmViewModel : ViewModel() {

    private val _progress = MutableStateFlow<Boolean?>(null)
    val progress get() = _progress.asStateFlow()

    private val _error = MediatorLiveData<Throwable>()
    val error: LiveData<Throwable> get() = _error

    // optional flags
//    val noInternetConnectionEvent by lazy { SingleLiveEvent<Unit>() }
//    val connectTimeoutEvent by lazy { MutableLiveData<SingleEvent<Unit>>() }
//    val forceUpdateAppEvent by lazy { MutableLiveData<SingleEvent<Unit>>() }
//    val serverMaintainEvent by lazy { MutableLiveData<SingleEvent<Unit>>() }
//    val unknownErrorEvent by lazy { MutableLiveData<SingleEvent<Unit>>() }

    private val handler = CoroutineExceptionHandler { _, exception ->
        Timber.tag(COROUTINE_EXCEPTION_HANDLER_MESSAGE).e(exception)
        // handleError(exception)
    }

    protected fun safeLaunch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(handler, block = block)
    }

    open fun showProgress() {
        _progress.value = true
    }

    open fun hideProgress() {
        _progress.value = false
    }

    open fun passError(throwable: Throwable, showSystemError: Boolean = true) {
        if (showSystemError) {
            _error.value = throwable
            _error.value = _error.value
            _error.postValue(throwable)
            _error.setValue(throwable)
        }
    }

    protected suspend fun <T> call(
        callFlow: Flow<T>,
        completionHandler: (collect: T) -> Unit = {}
    ) {
        callFlow
            .catch { passError(throwable = it) }
            .collect {
                completionHandler.invoke(it)
            }
    }

    protected suspend fun <T> callWithProgress(
        callFlow: Flow<T>,
        completionHandler: (collect: T) -> Unit= {}
    ) {
        callFlow
            .onStart { showProgress() }
            .onCompletion { hideProgress() }
            .catch { passError(throwable = it) }
            .collect {
                completionHandler.invoke(it)
            }
    }

    protected suspend fun <T> execute(
        callFlow: Flow<Resource<T>>,
        completionHandler: (collect: T) -> Unit= {}
    ) {
        callFlow
            .catch { passError(throwable = it) }
            .collect { resource ->
                when (resource.status) {
                    Resource.Status.ERROR -> passError(Exception(resource.message))
                    Resource.Status.SUCCESS -> {
                        completionHandler.invoke(resource.data!!)
                    }
                    else -> passError(Exception(resource.message))
                }
            }
    }

    protected suspend fun <T> executeWithProgress(
        callFlow: Flow<Resource<T>>,
        completionHandler: (collect: T) -> Unit= {}
    ) {
        callFlow
            .onStart { showProgress() }
            .onCompletion { hideProgress() }
            .catch { passError(throwable = it) }
            .collect { resource ->
                when (resource.status) {
                    Resource.Status.ERROR -> passError(Exception(resource.message))
                    Resource.Status.SUCCESS -> {
                        completionHandler.invoke(resource.data!!)
                    }
                    else -> passError(Exception(resource.message))
                }
            }
    }

    /*
    private fun handleError(throwable: Throwable) {
        when (throwable) {
                    // case no internet connection
                    is UnknownHostException -> {
                        noInternetConnectionEvent.call()
                    }
                    is ConnectException -> {
                        noInternetConnectionEvent.call()
                    }
                    // case request time out
                    is SocketTimeoutException -> {
                        connectTimeoutEvent.call()
                    }
                    else -> {
                        // convert throwable to base exception to get error information
                        val baseException = throwable.toBaseException()
                        when (baseException.httpCode) {
                            HttpURLConnection.HTTP_UNAUTHORIZED -> {
                                errorMessage.value = baseException.message
                            }
                            HttpURLConnection.HTTP_INTERNAL_ERROR -> {
                                errorMessage.value = baseException.message
                            }
                            else -> {
                                unknownErrorEvent.call()
                            }
                        }
                    }
                }
    }
*/

    companion object {
        private const val COROUTINE_EXCEPTION_HANDLER_MESSAGE = "MVVM-ExceptionHandler"
    }
}
