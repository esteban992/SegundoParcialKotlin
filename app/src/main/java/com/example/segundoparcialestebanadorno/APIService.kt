package com.example.segundoparcialestebanadorno

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url


interface APIService {

    @GET
    suspend fun getRandomJoke (@Url url: String): Response<JokeResponse>

    @GET (value = "random?category={category}")
    fun getRandomJokeByCategory(@Path("category") category: String)


}