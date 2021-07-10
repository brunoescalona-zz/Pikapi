package com.escalona.pikapi.data.remote

import com.escalona.pikapi.data.remote.models.pokemon.PokemonEntity
import retrofit2.http.GET

interface PokeApiService {

    @GET("pokemon/")
    suspend fun getPokemons(): List<PokemonEntity>
}