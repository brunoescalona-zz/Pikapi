package com.escalona.pikapi

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.escalona.pikapi.domain.pokemon.Pokemon

@Composable
fun PokemonListView(viewModel: MainViewModel) {
    val pokemonList: List<Pokemon> by viewModel.pokemonList.observeAsState(emptyList())
    LazyColumn {
        items(pokemonList) { pokemon ->
            Text(text = pokemon.name)
        }
    }
}