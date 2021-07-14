package com.escalona.pikapi

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.escalona.pikapi.domain.pokemon.Pokemon

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PokemonListView(viewModel: MainViewModel) {
    val pokemonList: List<Pokemon> by viewModel.pokemonList.observeAsState(emptyList())
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {
        items(pokemonList) { pokemon ->
            Card(
                modifier = Modifier
                    .height(200.dp)
                    .padding(8.dp),
                elevation = 10.dp
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = rememberImagePainter(
                            data = pokemon.imageUrl,
                            builder = {
                                crossfade(true)
                            }
                        ),
                        contentScale = ContentScale.Fit,
                        contentDescription = pokemon.name,
                        modifier = Modifier.fillMaxSize()
                    )
                    Text(text = pokemon.name)
                }
            }
        }
    }
}