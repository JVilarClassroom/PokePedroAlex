package es.uji.al375555.pokeuji

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.widget.Toast
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import es.uji.al375555.pokeuji.PokemonActivity.Pokemon
import es.uji.al375555.pokeuji.PokemonActivity.PokemonView
import es.uji.al375555.pokeuji.PokemonActivity.PokemonViewModel
import es.uji.al375555.pokeuji.SpeciesActivity.SpeciesActivity

class MainActivity : AppCompatActivity(), PokemonView {

    private val viewModel: PokemonViewModel = PokemonViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val pokemonTextSpecies = findViewById<TextView>(R.id.pokemonTextSpecies)
        pokemonTextSpecies.setOnClickListener { pokemonToSpecies() }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val pokemonSearchText = findViewById<EditText>(R.id.pokemonSearch)
        val pokemonSearchButton = findViewById<Button>(R.id.bPokemonSearch)

        pokemonSearchButton.setOnClickListener {
            val searchText = pokemonSearchText.text.toString()
            viewModel.onPokemonSearchRequested(searchText)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.view = this
    }

    override fun onPause() {
        super.onPause()
        viewModel.view = null
    }

    private fun pokemonToSpecies() {
        val intent = Intent(this, SpeciesActivity::class.java)
        startActivity(intent)
    }

    @SuppressLint("SetTextI18n", "CheckResult")
    override fun showPokemonData(pokemon: Pokemon) {

        val pokemonTextName = findViewById<TextView>(R.id.pokemonTextName)
        val pokemonTextWeight = findViewById<TextView>(R.id.pokemonTextWeight)
        val pokemonTextHeight = findViewById<TextView>(R.id.pokemonTextHeight)
        val pokemonImage = findViewById<ImageView>(R.id.pokemonImagePokemon)

        pokemonTextName.text = pokemon.name
        pokemonTextWeight.text = "Weight: "+(pokemon.weight / 10f).toString()+" kg"
        pokemonTextHeight.text = "Height: "+(pokemon.height / 10f).toString()+" m"
        Glide.with(pokemonImage.context).load(pokemon.photo)
    }

    override fun showSearchError(error: Throwable) {
        Toast.makeText(this, "Error al buscar Pokémon: ${error.message}", Toast.LENGTH_SHORT).show()
        Log.e("MainActivity", "Error al buscar Pokémon", error)
    }

}