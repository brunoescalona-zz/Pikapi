package com.escalona.pikapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.escalona.pikapi.data.PokeApiRepository
import com.escalona.pikapi.domain.pokemon.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: PokeApiRepository
) : ViewModel() {

    private val _pokemonList = MutableLiveData<List<Pokemon>>()
    val pokemonList: LiveData<List<Pokemon>>
        get() = _pokemonList

    init {
        viewModelScope.launch(Dispatchers.Default) {
            repository.getPokemons().collect { _pokemonList.postValue(it) }
        }
    }
}