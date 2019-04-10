package com.kkk.githubpaging.data.vo

import androidx.paging.PagedList

data class GithubUIModel(val list: PagedList<RepoVO>? = null, val error: Throwable? = null)