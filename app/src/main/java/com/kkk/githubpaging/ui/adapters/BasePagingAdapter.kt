package com.kkk.githubpaging.ui.adapters

import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.kkk.githubpaging.data.vo.BaseVO
import com.kkk.githubpaging.ui.viewholders.BaseViewHolder

abstract class BasePagingAdapter<B : BaseViewHolder<O>,O:BaseVO> : PagedListAdapter<O, B>( object : DiffUtil.ItemCallback<O>() {
    override fun areItemsTheSame(oldItem: O, newItem: O): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: O, newItem: O): Boolean {
        return oldItem == newItem
    }

}) {

    override fun onBindViewHolder(holder: B, position: Int) {
        holder.setData(getItem(position)!!)
    }

}
