package com.kkk.githubpaging.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.kkk.githubpaging.data.vo.RepoVO
import com.kkk.githubpaging.di.testAppModule
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.rules.TestRule



class MainViewModelTest : KoinTest {
    private val viewModel: MainViewModel by inject()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var listObserver: Observer<LiveData<PagedList<RepoVO>>>

    @Mock
    private lateinit var errorObserver: Observer<String>

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        startKoin(testAppModule)
    }


    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun getGithubRepositoryList() {
        viewModel.getGithubRepoList().observeForever(listObserver)
        viewModel.getErrorMessage().observeForever(errorObserver)
        viewModel.loadGithubRepoList("MVVM Kotlin",1,10)

        val value = viewModel.getGithubRepoList().value ?: error("No value for view myModel")
        Mockito.verify(listObserver).onChanged(value)
    }
}