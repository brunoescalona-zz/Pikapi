package com.escalona.pikapi.data

import com.escalona.pikapi.data.remote.PokeApiService
import com.escalona.pikapi.data.remote.models.getResourceId
import com.escalona.pikapi.data.remote.models.pokemon.mapper
import com.escalona.pikapi.domain.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokeApiRepositoryImpl(
    private val service: PokeApiService
) : PokeApiRepository {

    override fun getPokemons(): Flow<List<Pokemon>> = flow {
        val pokemons = service.getPokemons().results
            .map { it.getResourceId() }
            .map { service.getPokemon(it) }
            .map { it.mapper() }
        emit(pokemons)
    }
}