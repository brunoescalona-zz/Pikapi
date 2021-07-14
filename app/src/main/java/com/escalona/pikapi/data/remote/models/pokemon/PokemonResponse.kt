package com.escalona.pikapi.data.remote.models.pokemon

import com.escalona.pikapi.data.local.models.pokemon.PokemonEntity
import com.escalona.pikapi.domain.pokemon.Pokemon
import com.squareup.moshi.Json

data class PokemonResponse(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "sprites") val sprites: Sprites
)

data class Sprites(
    @field:Json(name = "front_default") val frontDefault: String,
)

fun PokemonResponse.domainMapper() = Pokemon(id, name, sprites.frontDefault)
fun PokemonResponse.entityMapper() = PokemonEntity(id, name, sprites.frontDefault)