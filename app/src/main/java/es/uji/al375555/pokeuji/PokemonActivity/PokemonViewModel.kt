package es.uji.al375555.pokeuji.PokemonActivity

import androidx.lifecycle.ViewModel
import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonViewModel(private val cachedAPI: CachedAPI): ViewModel() {

    var view: PokemonView? = null

    private val pokemonRepository = PokemonRepository(cachedAPI)
    private var currentPokemon: Pokemon? = null

    fun onPokemonSearchRequested(query: String) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val pokemon = pokemonRepository.getPokemon(query)
                currentPokemon = pokemon
                view?.showPokemonData(pokemon)
                view?.showPokemonSprite(pokemon.photo)
            } catch (e: Exception) {
                view?.showSearchError(e)
                Log.e("PokemonViewModel", "Error fetching Pokemon", e)
            }
        }
    }

    fun getCurrentPokemon(): Pokemon {
        return pokemonRepository.getCurrentPokemon()
    }

    suspend fun loadPokemonSpecies(id: String): SpeciesName {
        return pokemonRepository.getPokemonSpecies(id)
    }
}