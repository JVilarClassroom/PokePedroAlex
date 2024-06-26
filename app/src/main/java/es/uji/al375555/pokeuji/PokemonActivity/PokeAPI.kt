package es.uji.al375555.pokeuji.PokemonActivity

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import es.uji.al375555.pokeuji.SpeciesActivity.PokemonDescription
import es.uji.al375555.pokeuji.SpeciesActivity.VarietiesResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface PokeAPI {
    @Headers("Accept: application/json")
    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: String): PokemonResponse

    @Headers("Accept: application/json")
    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpecies(@Path("id") id: String): SpeciesName

//    @Headers("Accept: application/json")
//    @GET("pokemon-species/{id}")
//    suspend fun getPokemonDescription(@Path("id") id: String): PokemonDescription

//    @Headers("Accept: application/json")
//    @GET("pokemon-species/{id}")
//    suspend fun getPokemonVarieties(@Path("id") id: String): VarietiesResponse

    companion object {
        fun create(): PokeAPI {
            val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
            return retrofit.create(PokeAPI::class.java)
        }
    }
}
