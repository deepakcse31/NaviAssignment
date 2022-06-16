package com.example.githubscreen.data.api

import com.example.githubscreen.model.githubresponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("deepakcse31")
    suspend fun getUsers(): Response<githubresponse>
}