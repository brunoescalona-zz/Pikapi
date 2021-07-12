package com.escalona.pikapi.data

import com.escalona.pikapi.domain.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokeApiRepository {

    /**
     * Return the list of pokemons
     */
    fun getPokemons(): Flow<List<Pokemon>>
}