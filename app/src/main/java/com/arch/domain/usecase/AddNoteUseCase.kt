package com.arch.domain.usecase

import com.arch.domain.model.ToDoData
import com.arch.domain.repo.IAddNoteRepo
import com.arch.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(private val repo: IAddNoteRepo, private val dispatcher: CoroutineDispatcher): BaseUseCase<String, ToDoData>(dispatcher) {
    override suspend fun block(param: String):ToDoData {
       return repo.addNote(param)
    }


}