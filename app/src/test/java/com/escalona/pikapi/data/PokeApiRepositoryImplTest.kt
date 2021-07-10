package com.escalona.pikapi.data

import com.escalona.pikapi.data.remote.PokeApiService
import com.escalona.pikapi.data.remote.models.pokemon.PokemonEntity
import com.escalona.pikapi.domain.pokemon.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class PokeApiRepositoryImplTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private val mockedPokeApiService: PokeApiService = mock()
    private val pokeApiRepository = PokeApiRepositoryImpl(mockedPokeApiService)

    private val pokemonEntityList = listOf(
        PokemonEntity(1, "Pikachu"),
        PokemonEntity(2, "Charmander"),
        PokemonEntity(3, "Bulbasaur")
    )

    private val pokemonList = listOf(
        Pokemon(1, "Pikachu"),
        Pokemon(2, "Charmander"),
        Pokemon(3, "Bulbasaur")
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `get a list of pokemons`() = runBlockingTest {
        whenever(mockedPokeApiService.getPokemons()).thenReturn(pokemonEntityList)

        pokeApiRepository
            .getPokemons()
            .collect { assert(it == pokemonList) }
    }
}