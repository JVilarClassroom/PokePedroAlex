package es.uji.al375555.pokeuji

import android.annotation.SuppressLint
import android.content.Intent
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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import es.uji.al375555.pokeuji.PokemonActivity.Pokemon
import es.uji.al375555.pokeuji.PokemonActivity.PokemonView
import es.uji.al375555.pokeuji.PokemonActivity.PokemonViewModel
import es.uji.al375555.pokeuji.SpeciesActivity.SpeciesActivity

class MainActivity : AppCompatActivity(), PokemonView {

    private val viewModel: PokemonViewModel = PokemonViewModel()
    private var pokemonName: String? = null

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

        val pokemonButtonTypes = findViewById<Button>(R.id.pokemonButtonTypes)
        pokemonButtonTypes.setOnClickListener {
            showTypesDialog()
        }

        val pokemonButtonMoves = findViewById<Button>(R.id.pokemonButtonAbilities)
        pokemonButtonMoves.setOnClickListener {
            showMovesDialog()
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
        pokemonName?.let {
            val intent = Intent(this, SpeciesActivity::class.java)
            intent.putExtra("POKEMON_NAME", it)
            startActivity(intent)
        }
    }

    @SuppressLint("SetTextI18n", "CheckResult")
    override fun showPokemonData(pokemon: Pokemon) {
        pokemonName = pokemon.name

        val pokemonTextName = findViewById<TextView>(R.id.pokemonTextName)
        val pokemonTextWeight = findViewById<TextView>(R.id.pokemonTextWeight)
        val pokemonTextHeight = findViewById<TextView>(R.id.pokemonTextHeight)
        val pokemonTextSpecies = findViewById<TextView>(R.id.pokemonTextSpecies)

        pokemonTextName.text = pokemonName
        pokemonTextWeight.text = "Weight: "+(pokemon.weight / 10f).toString()+" kg"
        pokemonTextHeight.text = "Height: "+(pokemon.height / 10f).toString()+" m"
        pokemonTextSpecies.text = "Species: "+pokemon.species.name
    }

    override fun showPokemonSprite(spriteUrl: String?) {

        val pokemonImage = findViewById<ImageView>(R.id.pokemonImagePokemon)

        Glide.with(this)
            .load(spriteUrl)
            .placeholder(R.drawable.caution)
            .error(R.drawable.caution)
            .into(pokemonImage)
    }

    override fun showSearchError(error: Throwable) {
        Toast.makeText(this, "Error al buscar Pokémon", Toast.LENGTH_SHORT).show()
    }

    private fun showTypesDialog() {
        val currentPokemon = viewModel.getCurrentPokemon()
        val types = currentPokemon.types.joinToString(separator = "\n") { it.name }
        MaterialAlertDialogBuilder(this)
            .setTitle("Types of ${currentPokemon.name}")
            .setMessage(types)
            .setNegativeButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun showMovesDialog() {
        val currentPokemon = viewModel.getCurrentPokemon()
        val moveNames = currentPokemon.abilities
        val message = moveNames.joinToString(separator = "\n")
        MaterialAlertDialogBuilder(this)
            .setTitle("Abilities of ${currentPokemon.name}")
            .setMessage(message)
            .setNegativeButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}