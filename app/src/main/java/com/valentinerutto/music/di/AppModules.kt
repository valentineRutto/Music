package com.valentinerutto.music.di

import com.valentinerutto.music.AlbumsViewmodel
import com.valentinerutto.music.App
import com.valentinerutto.music.network.ApiService
import com.valentinerutto.music.network.RetrofitClient.createOkClient
import com.valentinerutto.music.network.RetrofitClient.createRetrofit
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModules = module {
    single { App.INSTANCE }
    single<ApiService> { (get() as Retrofit).create(ApiService::class.java) }
    single { createOkClient() }

    single {
        createRetrofit(baseUrl = "https://itunes.apple.com/us/rss/topalbums/", get())
    }
    viewModel { AlbumsViewmodel(get()) }

}