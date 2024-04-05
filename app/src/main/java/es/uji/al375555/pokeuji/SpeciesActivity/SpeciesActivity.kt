package es.uji.al375555.pokeuji.SpeciesActivity

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.uji.al375555.pokeuji.EvolutionActivity.EvolutionActivity
import es.uji.al375555.pokeuji.R

class SpeciesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_species)

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

}