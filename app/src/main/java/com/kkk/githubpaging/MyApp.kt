package com.kkk.githubpaging

import android.app.Application
import com.kkk.githubpaging.data.datasource.GithubDataSource
import com.kkk.githubpaging.data.datasource.factory.GithubDataSourceFactory
import com.kkk.githubpaging.data.repository.GithubRepository
import com.kkk.githubpaging.data.repository.GithubRepositoryImpl
import com.kkk.githubpaging.di.createOkHttpClient
import com.kkk.githubpaging.di.createWebService
import com.kkk.githubpaging.network.ApiService
import com.kkk.githubpaging.network.rx.AndroidSchedulerProvider
import com.kkk.githubpaging.network.rx.SchedulerProvider
import com.kkk.githubpaging.utility.AppConstants
import com.kkk.githubpaging.viewmodel.MainViewModel
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.Kodein.Companion.lazy
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class MyApp:Application(),KodeinAware{
    override val kodein: Kodein by lazy {

        // provided web components
        bind<OkHttpClient>() with   singleton { createOkHttpClient()  }
        bind<ApiService>() with singleton { createWebService(okHttpClient = instance(), url = AppConstants.BASE_URL) }

        //provide schedule provider
        bind<SchedulerProvider>() with singleton { AndroidSchedulerProvider() }

        //provide data source
        bind() from singleton { GithubDataSource(instance(),instance()) }
        bind() from singleton { GithubDataSourceFactory(instance()) }

        //provide data repository
        bind<GithubRepository>() with  singleton { GithubRepositoryImpl(instance()) }

//    ViewModel for Home
        bind() from singleton { MainViewModel(instance()) }
    }

}