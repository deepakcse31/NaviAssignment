package com.example.githubscreen.di

import android.content.Context
import com.example.githubscreen.BuildConfig
import com.example.githubscreen.data.api.ApiHelper
import com.example.githubscreen.data.api.ApiHelperImpl
import com.example.githubscreen.data.api.ApiService
import com.example.githubscreen.network.NetworkConnectionLiveData
import com.example.githubscreen.utils.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun provideBaseUrl() = "https://api.github.com/users/"

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext appContext: Context): NetworkHelper {
        return NetworkHelper(appContext)
    }

    @Provides
    @Singleton
    fun provideNetworkconnection(@ApplicationContext appContext: Context): NetworkConnectionLiveData {
        return NetworkConnectionLiveData(appContext)
    }

}