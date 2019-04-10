package com.kkk.githubpaging.network

import com.kkk.githubpaging.data.vo.RepoResponseVO
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/repositories?sort=stars")
    fun searchRepos(@Query("q") query: String,
                    @Query("page") page: Int,
                    @Query("per_page") itemsPerPage: Int): Observable<RepoResponseVO>
}