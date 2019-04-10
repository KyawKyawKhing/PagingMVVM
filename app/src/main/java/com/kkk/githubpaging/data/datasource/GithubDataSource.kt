package com.kkk.githubpaging.data.datasource

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.kkk.githubpaging.data.vo.RepoVO
import com.kkk.githubpaging.network.ApiService
import com.kkk.githubpaging.network.rx.SchedulerProvider

class GithubDataSource(
    private val apiService: ApiService,
    private val schedulerProvider: SchedulerProvider
) : PageKeyedDataSource<Int, RepoVO>() {

    var searchQuery:String=""
    val mErrorLiveData: MutableLiveData<String> = MutableLiveData()

    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, RepoVO>) {
        apiService.searchRepos(searchQuery,1,10)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.mainThread())
            .subscribe({
                callback.onResult(it.items, null, 2)
            }, {
                mErrorLiveData.postValue(it.localizedMessage)
            })
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, RepoVO>) {
        apiService.searchRepos(searchQuery,params.key,10)
            .subscribe({
                callback.onResult(it.items, params.key + 1)
            }, {
                mErrorLiveData.postValue(it.localizedMessage)
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, RepoVO>) {
    }
}