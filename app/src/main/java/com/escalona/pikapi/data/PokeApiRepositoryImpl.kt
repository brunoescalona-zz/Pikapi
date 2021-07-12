package com.escalona.pikapi.data

import com.escalona.pikapi.data.local.PokemonDao
import com.escalona.pikapi.data.local.models.pokemon.PokemonEntity
import com.escalona.pikapi.data.local.models.pokemon.domainMapper
import com.escalona.pikapi.data.remote.PokeApiService
import com.escalona.pikapi.data.remote.models.getResourceId
import com.escalona.pikapi.data.remote.models.pokemon.domainMapper
import com.escalona.pikapi.data.remote.models.pokemon.entityMapper
import com.escalona.pikapi.domain.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokeApiRepositoryImpl @Inject constructor(
    private val service: PokeApiService,
    private val pokemonDao: PokemonDao
) : PokeApiRepository {

    override fun getPokemons(): Flow<List<Pokemon>> = flow {
        val entityPokemons = pokemonDao.getAll()
        if (entityPokemons == emptyList<PokemonEntity>()) {
            val domainPokemons = service.getPokemons().results
                .map { it.getResourceId() }
                .map { service.getPokemon(it) }
                .apply { pokemonDao.insertAll(*this.map { it.entityMapper() }.toTypedArray()) }
                .map { it.domainMapper() }
            emit(domainPokemons)
        } else {
            emit(entityPokemons.map { it.domainMapper() })
        }
    }
}