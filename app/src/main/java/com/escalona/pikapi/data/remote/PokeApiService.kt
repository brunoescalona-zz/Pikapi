package com.escalona.pikapi.data.remote

import com.escalona.pikapi.data.remote.models.Response
import com.escalona.pikapi.data.remote.models.pokemon.PokemonEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {

    @GET("pokemon/")
    suspend fun getPokemons(): Response

    @GET("pokemon/{pokemonId}")
    suspend fun getPokemon(@Path("pokemonId") pokemonId: Int): PokemonEntity
}