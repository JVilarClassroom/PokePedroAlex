package es.uji.al375555.pokeuji.PokemonActivity

data class Pokemon(
    val id: String,
    val name: String,
    val photo: String,
    val weight: Int,
    val height: Int,
    val species: SpeciesResponse,
    val types: List<Type>,
    val abilities: List<String>
)

data class Type(
    val name: String
)