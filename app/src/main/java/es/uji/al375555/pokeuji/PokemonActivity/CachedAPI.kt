package es.uji.al375555.pokeuji.PokemonActivity

class CachedAPI(private val pokeAPI: PokeAPI) {
    private val cache: MutableMap<String, Any> = mutableMapOf()

    suspend fun getPokemon(id: String): PokemonResponse {
        val cachedResult = cache["getPokemon:$id"]
        if (cachedResult != null) {
            return cachedResult as PokemonResponse
        }
        val result = pokeAPI.getPokemon(id)
        cache["getPokemon:$id"] = result
        return result
    }
}