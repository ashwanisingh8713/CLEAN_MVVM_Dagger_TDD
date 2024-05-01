package com.arch.app.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arch.domain.ToDoResponse
import com.arch.domain.model.ToDoData
import com.arch.domain.usecase.AddNoteUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val addNoteUseCase: AddNoteUseCase): ViewModel() {

    private val _success = MutableStateFlow<ToDoData?>(null)
    val success = _success.asStateFlow()


    private val _error = MutableSharedFlow<String?>()
    val error = _error.asSharedFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()


    fun addNote(note: String) {
        viewModelScope.launch {
            addNoteUseCase.invoke(scope = viewModelScope, param = note,
                result = object : ToDoResponse<ToDoData> {

                override fun onLoading(isLoading: Boolean) {
                    _isLoading.value = isLoading
                }

                override fun onError(exception: Exception) {
                    exception.message?.let { _error.tryEmit(it) }
                }

                override fun onSuccess(t: ToDoData) {
                    _success.value = t
                }

            })
        }
    }

}