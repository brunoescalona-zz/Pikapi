package com.escalona.pikapi.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.escalona.pikapi.data.local.models.pokemon.PokemonEntity

@Database(
    entities = [PokemonEntity::class],
    version = 1
)
abstract class PokemonDataBase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}