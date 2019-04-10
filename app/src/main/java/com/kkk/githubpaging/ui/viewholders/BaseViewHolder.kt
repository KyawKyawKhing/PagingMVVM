package com.kkk.githubpaging.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<O>(itemView: View):RecyclerView.ViewHolder(itemView) {
    abstract fun setData(data: O)
}