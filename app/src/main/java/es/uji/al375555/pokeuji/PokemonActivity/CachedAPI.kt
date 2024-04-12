package es.uji.al375555.pokeuji.PokemonActivity

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CachedAPI(private val pokeAPI: PokeAPI) {
    private val cache: MutableMap<String, Any> = mutableMapOf()

    suspend fun getPokemon(id: String): PokemonResponse {
        val cachedResult = cache["getPokemon:$id"]
        if (cachedResult != null) {
            return cachedResult as PokemonResponse
        }
        val result = pokeAPI.getPokemon(id)
        cache["getPokemon:$id"] = result
        return result
    }

    suspend fun getPokemonSpecies(id: String): SpeciesName {
        val cachedResult = cache["getPokemonSpecies:$id"]
        if (cachedResult != null) {
            return cachedResult as SpeciesName
        }
        return withContext(Dispatchers.IO) {
            val speciesResponse = pokeAPI.getPokemonSpecies(id)
            cache["getPokemonSpecies:$id"] = speciesResponse
            speciesResponse
        }
    }

    companion object {
        fun create(): CachedAPI {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

            val pokeAPI = retrofit.create(PokeAPI::class.java)
            return CachedAPI(pokeAPI)
        }
    }
}