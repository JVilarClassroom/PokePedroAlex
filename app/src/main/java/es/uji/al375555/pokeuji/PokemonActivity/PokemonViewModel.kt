package es.uji.al375555.pokeuji.PokemonActivity

import androidx.lifecycle.ViewModel
import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonViewModel: ViewModel() {

    var view: PokemonView? = null

    private val pokemonRepository = PokemonRepository()

    fun onPokemonSearchRequested(query: String) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val pokemon = pokemonRepository.getPokemon(query)
                view?.showPokemonData(pokemon)
            } catch (e: Exception) {
                view?.showSearchError(e)
                Log.e("PokemonViewModel", "Error fetching Pokemon", e)
            }
        }
    }
}