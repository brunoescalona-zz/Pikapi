package com.escalona.pikapi.data.remote.models.pokemon

import com.escalona.pikapi.domain.pokemon.Pokemon
import com.squareup.moshi.Json

data class PokemonEntity(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String
)

fun PokemonEntity.mapper() = Pokemon(id, name)