package com.escalona.pikapi.di

import android.content.Context
import androidx.room.Room
import com.escalona.pikapi.data.local.PokemonDao
import com.escalona.pikapi.data.local.PokemonDataBase
import com.escalona.pikapi.data.remote.PokeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object PikapiModule {

    @Provides
    fun providePokeApiService(): PokeApiService {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(PokeApiService::class.java)
    }

    @Provides
    fun providePokemonDao(
        @ApplicationContext context: Context
    ): PokemonDao {
        return Room
            .databaseBuilder(
                context,
                PokemonDataBase::class.java,
                "pokemon-database"
            )
            .build()
            .pokemonDao()
    }
}