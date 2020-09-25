package com.haag.news_app.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {


    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Singleton
    @Provides
    fun NewsApi(OkHttpClient: OkHttpClient): NewsAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl("https://api.chucknorris.io/jokes/")
            .baseUrl("https://newsapi.org/v2/")
            .client(OkHttpClient)
            .build()
            .create(NewsAPI::class.java)

        //top-headlines?country=us&apiKey=05588972af7943188910742037f61d0f
    }
}