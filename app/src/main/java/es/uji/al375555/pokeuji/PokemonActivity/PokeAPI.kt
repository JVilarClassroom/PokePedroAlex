package es.uji.al375555.pokeuji.PokemonActivity

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface PokeAPI {
    @Headers("Accept: application/json")
    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: String): PokemonResponse

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
