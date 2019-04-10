package com.kkk.githubpaging.data.datasource.factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.kkk.githubpaging.data.datasource.GithubDataSource
import com.kkk.githubpaging.data.vo.RepoResponseVO
import com.kkk.githubpaging.data.vo.RepoVO
import com.kkk.githubpaging.network.ApiService
import com.kkk.githubpaging.network.rx.AndroidSchedulerProvider
import com.kkk.githubpaging.network.rx.SchedulerProvider
import io.reactivex.Observable


class GithubDataSourceFactory(private val githubDataSource: GithubDataSource): DataSource.Factory<Int, RepoVO>() {

    var query:String=""

    private var dataSourceLiveData: MutableLiveData<GithubDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, RepoVO> {
        githubDataSource.searchQuery = query
        dataSourceLiveData.postValue(githubDataSource)
        return githubDataSource
    }

    fun getDataSourceLiveData(): MutableLiveData<GithubDataSource> {
        return dataSourceLiveData
    }
}