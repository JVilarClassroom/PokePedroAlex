package es.uji.al375555.pokeuji.SpeciesActivity

import es.uji.al375555.pokeuji.PokemonActivity.CachedAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SpeciesRepository(private val pokeAPI: CachedAPI) {
//    suspend fun getPokemonDescription(pokemonName: String): String {
//        return withContext(Dispatchers.IO) {
//            val description = pokeAPI.getPokemonDescription(pokemonName)
//            val flavorText = description.flavortext.firstOrNull()?.flavor_text
//            flavorText ?: "No description available"
//        }
//    }

//    suspend fun getPokemonDescription(pokemonName: String): PokemonDescription {
//        return withContext(Dispatchers.IO) {
//            cachedAPI.getPokemonDescription(pokemonName)
//        }
//    }
}