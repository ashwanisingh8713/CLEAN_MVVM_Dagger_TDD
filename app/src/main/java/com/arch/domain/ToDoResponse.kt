package com.arch.domain

import java.lang.Exception

interface ToDoResponse<T> {
    fun onLoading(isLoading: Boolean)
    fun onSuccess(t: T)

    fun onError(exception: Exception)
}