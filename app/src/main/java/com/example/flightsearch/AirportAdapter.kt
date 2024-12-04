package com.example.flightsearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AirportAdapter(private val onAirportSelected: (String) -> Unit) :
    RecyclerView.Adapter<AirportAdapter.AirportViewHolder>() {

    private val airports = mutableListOf<Airport>()

    fun submitList(newAirports: List<Airport>) {
        airports.clear()
        airports.addAll(newAirports)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirportViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_airport, parent, false)
        return AirportViewHolder(view)
    }

    override fun onBindViewHolder(holder: AirportViewHolder, position: Int) {
        val airport = airports[position]
        holder.bind(airport)
    }

    override fun getItemCount(): Int = airports.size

    inner class AirportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvIataCode: TextView = itemView.findViewById(R.id.tvIataCode)
        private val tvAirportName: TextView = itemView.findViewById(R.id.tvAirportName)

        fun bind(airport: Airport) {
            tvIataCode.text = airport.iataCode
            tvAirportName.text = airport.name
            itemView.setOnClickListener { onAirportSelected(airport.iataCode) }
        }
    }
}
