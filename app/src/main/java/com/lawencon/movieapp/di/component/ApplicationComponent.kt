package com.lawencon.movieapp.di.component

import android.app.Application
import com.lawencon.movieapp.MainActivity
import com.lawencon.movieapp.di.builder.ActivityBuilder
import com.lawencon.movieapp.di.module.ApplicationModule
import com.lawencon.movieapp.di.module.NetModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
    ApplicationModule::class,
    NetModule::class,
    ActivityBuilder::class,
    AndroidInjectionModule::class
))

interface ApplicationComponent {

    fun Inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}