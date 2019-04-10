package com.kkk.githubpaging.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.kkk.githubpaging.data.repository.GithubRepository
import com.kkk.githubpaging.data.vo.RepoVO

class MainViewModel(private val repository: GithubRepository) : BaseViewModel() {

    fun loadGithubRepoList(query:String,page:Int,itemsPerPage:Int) {
        repository.loadGithubList(query,page,itemsPerPage)
    }

    fun getGithubRepoList():LiveData<LiveData<PagedList<RepoVO>>>{
        return repository.getGithubRepoList()
    }
    fun getErrorMessage():LiveData<String>{
        return repository.getErrorMessage()
    }

}
