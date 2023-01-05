package com.valentinerutto.music.di

import androidx.room.Room
import com.valentinerutto.music.AlbumsViewmodel
import com.valentinerutto.music.App
import com.valentinerutto.music.data.local.AlbumsDatabase
import com.valentinerutto.music.network.ApiService
import com.valentinerutto.music.network.RetrofitClient.createOkClient
import com.valentinerutto.music.network.RetrofitClient.createRetrofit
import com.valentinerutto.music.repository.AlbumsRepository
import com.valentinerutto.music.util.Constants
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModules = module {
    single { App.INSTANCE }
    single<ApiService> { (get() as Retrofit).create(ApiService::class.java) }
    single { createOkClient() }

    single {
        createRetrofit(baseUrl = Constants.BASE_URL, get())
    }
    single {
        Room.databaseBuilder(
            androidContext(), AlbumsDatabase::class.java,
            Constants.DB_NAME
        ).build()
    }

    single { get<AlbumsDatabase>().albumsDao }
    single { AlbumsRepository(apiService = get(), albumsDao = get()) }

    viewModel { AlbumsViewmodel(albumsRepository = get()) }

}