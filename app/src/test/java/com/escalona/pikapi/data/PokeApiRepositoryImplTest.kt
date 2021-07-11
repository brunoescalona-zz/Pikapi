package com.escalona.pikapi.data

import com.escalona.pikapi.data.remote.PokeApiService
import com.escalona.pikapi.data.remote.models.Response
import com.escalona.pikapi.data.remote.models.Result
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

    private val response = Response(
        count = 3,
        next = null,
        results = listOf(
            Result("pikachu", "https://www.pokeapi.com/v2/pokemon/1/"),
            Result("charmander", "https://www.pokeapi.com/v2/pokemon/2/"),
            Result("bulbasaur", "https://www.pokeapi.com/v2/pokemon/3/")
        )
    )
    private val pokemon1 = PokemonEntity(1, "pikachu")
    private val pokemon2 = PokemonEntity(2, "charmander")
    private val pokemon3 = PokemonEntity(3, "bulbasaur")

    private val pokemonList = listOf(
        Pokemon(1, "pikachu"),
        Pokemon(2, "charmander"),
        Pokemon(3, "bulbasaur")
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
        whenever(mockedPokeApiService.getPokemons()).thenReturn(response)
        whenever(mockedPokeApiService.getPokemon(1)).thenReturn(pokemon1)
        whenever(mockedPokeApiService.getPokemon(2)).thenReturn(pokemon2)
        whenever(mockedPokeApiService.getPokemon(3)).thenReturn(pokemon3)

        pokeApiRepository
            .getPokemons()
            .collect { assert(it == pokemonList) }
    }
}