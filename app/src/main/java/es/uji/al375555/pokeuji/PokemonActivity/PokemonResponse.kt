package es.uji.al375555.pokeuji.PokemonActivity

import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    val name: String,
    val sprites: Sprites,
    val weight: Int,
    val height: Int
)

data class Sprites(
    @Json(name = "front_default") val frontDefault: String,kghlkhg
)