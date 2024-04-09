package es.uji.al375555.pokeuji.PokemonActivity

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class PokemonRepository {
    private val api: PokeAPI

    init {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        api = retrofit.create(PokeAPI::class.java)
    }

    suspend fun getPokemon(id: String): Pokemon {
        return withContext(Dispatchers.IO) {
            val pokemonResponse = api.getPokemon(id.lowercase())
            val speciesResponse = pokemonResponse.species
            Pokemon(
                id = id,
                name = pokemonResponse.name,
                weight = pokemonResponse.weight,
                height = pokemonResponse.height,
                photo = pokemonResponse.sprites.frontDefault,
                species = speciesResponse
            )
        }
    }
}
