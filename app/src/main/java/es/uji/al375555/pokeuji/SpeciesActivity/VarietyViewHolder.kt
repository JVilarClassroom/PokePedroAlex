package es.uji.al375555.pokeuji.SpeciesActivity

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.uji.al375555.pokeuji.R

class VarietyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val varietyName: TextView = itemView.findViewById(R.id.textVarietyName)
}