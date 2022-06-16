package com.example.githubscreen.data.api

import com.example.githubscreen.model.githubresponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getUser() : Response<githubresponse>
}