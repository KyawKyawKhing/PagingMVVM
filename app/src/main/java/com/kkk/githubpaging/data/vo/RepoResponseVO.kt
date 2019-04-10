package com.kkk.githubpaging.data.vo

import com.google.gson.annotations.SerializedName

class RepoResponseVO(
    @SerializedName("total_count") val total: Int = 0,
    @SerializedName("items") val items: List<RepoVO> = emptyList(),
    val nextPage: Int? = null
)