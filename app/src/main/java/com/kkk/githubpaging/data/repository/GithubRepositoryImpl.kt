package com.kkk.githubpaging.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kkk.githubpaging.data.datasource.factory.GithubDataSourceFactory
import com.kkk.githubpaging.data.vo.RepoVO
import com.kkk.githubpaging.network.ApiService
import com.kkk.githubpaging.network.rx.SchedulerProvider
import java.util.concurrent.Executors

class GithubRepositoryImpl(
    private val dataSourceFactory: GithubDataSourceFactory) :
    GithubRepository {

    private var repoLiveData: MutableLiveData<LiveData<PagedList<RepoVO>>> = MutableLiveData()

    override fun loadGithubList(query: String, page: Int, itemsPerPage: Int) {
        dataSourceFactory.query = query

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPrefetchDistance(10)
            .setPageSize(10)
            .build()

        val livePageListBuilder = LivePagedListBuilder(dataSourceFactory, config)
            .setFetchExecutor(Executors.newFixedThreadPool(5))
            .setInitialLoadKey(1)
        val liveData = livePageListBuilder.build()
        repoLiveData.postValue(liveData)
    }

    override fun getGithubRepoList(): LiveData<LiveData<PagedList<RepoVO>>> {
        return repoLiveData
    }

    override fun getErrorMessage(): LiveData<String> {
        return Transformations.switchMap(dataSourceFactory.getDataSourceLiveData()) {
            it.mErrorLiveData
        }
    }

}