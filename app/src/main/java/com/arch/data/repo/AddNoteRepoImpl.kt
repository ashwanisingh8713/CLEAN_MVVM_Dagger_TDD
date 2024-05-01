package com.arch.data.repo

import com.arch.domain.model.ToDoData
import com.arch.domain.repo.IAddNoteRepo

class AddNoteRepoImpl(val dbObj: String):IAddNoteRepo {
    override suspend fun addNote(note: String): ToDoData {
        // todo write your db operation for insert
        return ToDoData(id = 0, note = note, isFave =  false)

    }
}