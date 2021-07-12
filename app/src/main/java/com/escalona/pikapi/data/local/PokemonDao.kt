package com.escalona.pikapi.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.escalona.pikapi.data.local.models.pokemon.PokemonEntity

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon")
    suspend fun getAll(): List<PokemonEntity>

    @Insert
    suspend fun insertAll(vararg pokemons: PokemonEntity)
}