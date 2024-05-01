package com.arch.domain.usecase.base

import com.arch.domain.ToDoResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseUseCase<P, T>(private val dispatcher: CoroutineDispatcher) {

    abstract suspend fun block(param: P):T

    suspend fun invoke(scope: CoroutineScope, param: P, result: ToDoResponse<T>) {

        scope.launch(dispatcher) {
            try {
                result.onLoading(true)
                val data = block(param)
                result.onSuccess(data)
                result.onLoading(false)
            } catch (e: Exception) {
                result.onLoading(false)
                result.onError(e)
            }

        }

    }

}