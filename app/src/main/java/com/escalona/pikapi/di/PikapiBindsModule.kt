package com.escalona.pikapi.di

import com.escalona.pikapi.data.PokeApiRepository
import com.escalona.pikapi.data.PokeApiRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PikapiBindsModule {
    @Binds
    abstract fun bindAnalyticsService(
        implementation: PokeApiRepositoryImpl
    ): PokeApiRepository
}