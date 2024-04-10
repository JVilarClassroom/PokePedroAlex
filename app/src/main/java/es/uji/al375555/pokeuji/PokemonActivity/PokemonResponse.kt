package es.uji.al375555.pokeuji.PokemonActivity

import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    val name: String,
    val species: SpeciesResponse,
    val weight: Int,
    val height: Int,
    val sprites: Sprites,
    val types: List<TypeWrapper>,
    val abilities: List<AbilityWrapper>
)

data class Sprites(
    @Json(name = "front_default") val frontDefault: String,
    @Json(name = "back_default") val backDefault: String?,
    @Json(name = "front_shiny") val frontShiny: String?,
    @Json(name = "back_shiny") val backShiny: String?,
    @Json(name = "front_female") val frontFemale: String?,
    @Json(name = "back_female") val backFemale: String?,
    @Json(name = "front_shiny_female") val frontShinyFemale: String?,
    @Json(name = "back_shiny_female") val backShinyFemale: String?,
)

data class SpeciesResponse(
    val name: String
)

data class TypeWrapper(
    val slot: Int,
    @Json(name = "type") val typeData: TypeData
)

data class TypeData(
    val name: String
)

data class AbilityWrapper(
    val ability: AbilityData
)

data class AbilityData(
    val name: String
)