package com.arch.domain.repo

import com.arch.domain.model.ToDoData

interface IAddNoteRepo {

    suspend fun addNote(note:String): ToDoData
}