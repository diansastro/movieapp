package com.lawencon.movieapp.di.builder

import com.lawencon.movieapp.view.genre.GenreListActivity
import com.lawencon.movieapp.view.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun bindGenreListActivity(): GenreListActivity

}