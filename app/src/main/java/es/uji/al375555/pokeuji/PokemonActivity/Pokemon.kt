package es.uji.al375555.pokeuji.PokemonActivity

data class Pokemon(
    val id: String,
    val name: String,
    val photo: String,
    val weight: Int,
    val height: Int,
    val species: SpeciesResponse
)
