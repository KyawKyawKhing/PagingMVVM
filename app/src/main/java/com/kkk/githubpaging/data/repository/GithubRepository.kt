package com.kkk.githubpaging.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.kkk.githubpaging.data.vo.GithubUIModel
import com.kkk.githubpaging.data.vo.RepoResponseVO
import com.kkk.githubpaging.data.vo.RepoVO
import io.reactivex.Observable

interface GithubRepository {
    fun loadGithubList(query:String,page:Int,itemsPerPage:Int)
    fun getGithubRepoList(): LiveData<LiveData<PagedList<RepoVO>>>
    fun getErrorMessage(): LiveData<String>
}