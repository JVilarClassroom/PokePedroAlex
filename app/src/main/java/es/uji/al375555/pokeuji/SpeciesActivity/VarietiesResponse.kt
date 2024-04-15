package es.uji.al375555.pokeuji.SpeciesActivity

data class VarietiesResponse(
    val varieties: List<VarietyResponse>
)

data class VarietyResponse(
    val isDefault: Boolean,
    val pokemon: PokemonRef
)

data class PokemonRef(
    val name: String,
    val url: String
)