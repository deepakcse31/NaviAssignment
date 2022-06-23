package com.example.githubscreen.data.api

import com.example.githubscreen.model.githubresponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("square/okhttp/pulls")
    suspend fun getUsers(): Response<githubresponse>
}