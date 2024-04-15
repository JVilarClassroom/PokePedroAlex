package es.uji.al375555.pokeuji.SpeciesActivity

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.uji.al375555.pokeuji.R
import es.uji.al375555.pokeuji.SpeciesActivity.Variety

class VarietiesAdapter(var varieties: List<Variety>) :
    RecyclerView.Adapter<VarietyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VarietyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_varieties, parent, false)
        return VarietyViewHolder(view)
    }

    override fun onBindViewHolder(holder: VarietyViewHolder, position: Int) {
        val variety = varieties[position]
        holder.varietyName.text = variety.name
    }

    override fun getItemCount(): Int {
        return varieties.size
    }
}