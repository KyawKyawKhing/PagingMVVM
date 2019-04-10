package com.kkk.githubpaging.ui.viewholders

import android.view.View
import com.kkk.githubpaging.data.vo.RepoVO
import kotlinx.android.synthetic.main.item_github.view.*

class RepoListViewHolder(itemView: View) : BaseViewHolder<RepoVO>(itemView) {
    override fun setData(data: RepoVO) {
        itemView.apply {
            tvTitle.text = data.fullName
            tvDescription.text = data.description ?: "No Description"
            tvLanguage.text = data.language
            tvStar.text = data.stars.toString()
            tvFork.text = data.forks.toString()
        }
    }
}