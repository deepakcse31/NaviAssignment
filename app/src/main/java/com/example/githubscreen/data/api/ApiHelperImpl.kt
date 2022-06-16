package com.example.githubscreen.data.api

import com.example.githubscreen.model.githubresponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private var apiService: ApiService) : ApiHelper {
    override suspend fun getUser(): Response<githubresponse> = apiService.getUsers()
}