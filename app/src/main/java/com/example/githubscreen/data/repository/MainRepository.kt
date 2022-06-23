package com.example.githubscreen.data.repository

import com.example.githubscreen.data.api.ApiHelper
import org.json.JSONObject
import retrofit2.http.Body
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getUsers() =  apiHelper.getUser()

}