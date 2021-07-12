package com.escalona.pikapi.data.remote.models.pokemon

import com.escalona.pikapi.data.local.models.pokemon.PokemonEntity
import com.escalona.pikapi.domain.pokemon.Pokemon
import com.squareup.moshi.Json

data class PokemonResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String
)

fun PokemonResponse.domainMapper() = Pokemon(id, name)
fun PokemonResponse.entityMapper() = PokemonEntity(id, name)