package es.uji.al375555.pokeuji.SpeciesActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.uji.al375555.pokeuji.EvolutionActivity.EvolutionActivity
import es.uji.al375555.pokeuji.PokemonActivity.CachedAPI
import es.uji.al375555.pokeuji.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SpeciesActivity : AppCompatActivity() {

    private val repository = SpeciesRepository(CachedAPI.create())
    private lateinit var varietiesAdapter: VarietiesAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_species)

        val pokemonName = intent.getStringExtra("POKEMON_NAME")
        val speciesPokemonName = findViewById<TextView>(R.id.speciesTextName)
        speciesPokemonName.text = pokemonName

        //loadPokemonDescription(pokemonName!!)

//        val varietyRecyclerView = findViewById<RecyclerView>(R.id.varietiesRecyclerView)
//        varietyRecyclerView.layoutManager = LinearLayoutManager(this)
//        varietiesAdapter = VarietiesAdapter(emptyList())
//        varietyRecyclerView.adapter = varietiesAdapter

        val speciesButtonEvolution = findViewById<TextView>(R.id.speciesButtonEvolution)
        speciesButtonEvolution.setOnClickListener { speciesToEvolution() }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun speciesToEvolution() {
        val intent = Intent(this, EvolutionActivity::class.java)
        startActivity(intent)
    }

//    private fun updateVarieties(varieties: List<Variety>) {
//        varietiesAdapter.varieties = varieties
//        varietiesAdapter.notifyDataSetChanged()
//    }

//    private fun loadPokemonDescription(pokemonName: String) {
//        CoroutineScope(Dispatchers.Main).launch {
//            try {
//                val flavorText = repository.getPokemonDescription(pokemonName)
//                findViewById<TextView>(R.id.speciesTextDescription).text = flavorText ?: "Description not available"
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }

//    private fun loadPokemonDescription(pokemonName: String) {
//        CoroutineScope(Dispatchers.Main).launch {
//            try {
//                val description = repository.getPokemonDescription(pokemonName)
//                val flavorText = description.flavortext.firstOrNull()?.flavor_text
//                findViewById<TextView>(R.id.speciesTextDescription).text = flavorText ?: "Description not available"
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }
}