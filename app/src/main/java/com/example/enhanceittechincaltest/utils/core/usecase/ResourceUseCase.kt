/*
 * Copyright (C) 2022, amansatija
 * All rights reserved.
 */
package com.example.enhanceittechincaltest.utils.core.usecase


import com.example.enhanceittechincaltest.utils.core.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class ResourceUseCase<in Params, ReturnType> where ReturnType : Any {

    protected abstract suspend fun FlowCollector<Resource<ReturnType>>.execute(params: Params)

    suspend operator fun invoke(params: Params) = flow {
        execute(params)
    }.flowOn(Dispatchers.IO)
}
