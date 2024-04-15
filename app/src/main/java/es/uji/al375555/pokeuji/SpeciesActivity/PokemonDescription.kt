package es.uji.al375555.pokeuji.SpeciesActivity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonDescription(
    val flavortext: List<FlavorTextEntry>
)

@JsonClass(generateAdapter = true)
data class FlavorTextEntry(
    val flavor_text: String,
    val version: String
)