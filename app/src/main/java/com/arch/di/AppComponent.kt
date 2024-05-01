package com.arch.di

import android.app.Application
import com.arch.UserApplication
import com.arch.app.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
}

    /*@Component.Builder
    interface Builder {
        @BindsInstance
        fun application(context: Application): Builder
        fun build(): AppComponent
    }
}*/