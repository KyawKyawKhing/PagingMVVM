package com.kkk.githubpaging.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kkk.githubpaging.R
import com.kkk.githubpaging.data.vo.RepoVO
import com.kkk.githubpaging.ui.viewholders.RepoListViewHolder

class RepoListAdapter : BasePagingAdapter<RepoListViewHolder, RepoVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_github, parent, false)
        return RepoListViewHolder(view)
    }
}