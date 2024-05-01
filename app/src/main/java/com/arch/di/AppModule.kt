package com.arch.di

import com.arch.data.repo.AddNoteRepoImpl
import com.arch.domain.usecase.AddNoteUseCase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
class AppModule {

    @Provides
    fun provideAddNoteUseCase(repo: AddNoteRepoImpl):AddNoteUseCase {
        return AddNoteUseCase(repo, Dispatchers.IO)
    }

    @Provides
    fun provideAddNoteRepo(@Named("DBInstance") dbObj: String):AddNoteRepoImpl {
        return AddNoteRepoImpl(dbObj = dbObj)
    }

    @Named("DBInstance")
    @Provides
    fun provideDB() : String {

        return ""
    }

}