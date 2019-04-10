package com.kkk.githubpaging.di

import com.kkk.githubpaging.network.rx.SchedulerProvider
import org.koin.dsl.module.module

val testRxModule = module {
    //provide schedule provider
    factory<SchedulerProvider> { TestSchedulerProvider() }
}
val testAppModule = listOf(
    testRxModule,
    remoteDataSourceModule,
    githubModule
)