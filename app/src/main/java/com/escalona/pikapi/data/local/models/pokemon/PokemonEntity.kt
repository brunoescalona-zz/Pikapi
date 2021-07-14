package com.escalona.pikapi.data.local.models.pokemon

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.escalona.pikapi.domain.pokemon.Pokemon

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "sprite_url") val spriteUrl: String
)

fun PokemonEntity.domainMapper() = Pokemon(id, name, spriteUrl)