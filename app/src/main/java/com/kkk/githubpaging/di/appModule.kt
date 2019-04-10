package com.kkk.githubpaging.di

import com.kkk.githubpaging.data.datasource.GithubDataSource
import com.kkk.githubpaging.data.datasource.factory.GithubDataSourceFactory
import com.kkk.githubpaging.data.repository.GithubRepository
import com.kkk.githubpaging.data.repository.GithubRepositoryImpl
import com.kkk.githubpaging.network.rx.AndroidSchedulerProvider
import com.kkk.githubpaging.network.rx.SchedulerProvider
import com.kkk.githubpaging.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val githubModule = module(definition = {

    //provide data source
    single { GithubDataSource(get(),get())}
    single { GithubDataSourceFactory(get())}

    //provide data repository
    single<GithubRepository> { GithubRepositoryImpl(get()) }

//    ViewModel for Home
    viewModel { MainViewModel(get()) }

})

val rxModule = module {
    //provide schedule provider
    factory<SchedulerProvider> { AndroidSchedulerProvider() }
}

val appModule = listOf(remoteDataSourceModule, rxModule, githubModule)
