package com.escalona.pikapi.data.remote

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object PokeApi {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val service = retrofit.create(PokeApiService::class.java)
}