package es.uji.al375555.pokeuji.PokemonActivity

interface PokemonView {
    fun showPokemonData(pokemon: Pokemon)
    fun showSearchError(error: Throwable)
}