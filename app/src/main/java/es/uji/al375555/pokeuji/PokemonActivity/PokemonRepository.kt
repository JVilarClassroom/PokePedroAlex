package es.uji.al375555.pokeuji.PokemonActivity

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class PokemonRepository(private val cachedAPI: CachedAPI) {

    private var lastSearchedPokemon: Pokemon? = null

    suspend fun getPokemon(id: String): Pokemon {
        return withContext(Dispatchers.IO) {
            val pokemonResponse = cachedAPI.getPokemon(id.lowercase())
            val pokemon = Pokemon(
                id = id,
                name = pokemonResponse.name,
                weight = pokemonResponse.weight,
                height = pokemonResponse.height,
                photo = pokemonResponse.sprites.frontDefault,
                species = pokemonResponse.species,
                types = pokemonResponse.types.map { Type(it.typeData.name) },
                abilities = pokemonResponse.abilities.map { it.ability.name }
            )
            lastSearchedPokemon = pokemon
            pokemon
        }
    }

    fun getCurrentPokemon(): Pokemon {
        return lastSearchedPokemon ?: throw IllegalStateException("No pokemon yet")
    }
}
