package com.example.flightsearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FlightAdapter(private val onFavoriteSelected: (String, String) -> Unit) :
    RecyclerView.Adapter<FlightAdapter.FlightViewHolder>() {

    private val flights = mutableListOf<Flight>()

    fun submitList(newFlights: List<Flight>) {
        flights.clear()
        flights.addAll(newFlights)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_flight, parent, false)
        return FlightViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        val flight = flights[position]
        holder.bind(flight)
    }

    override fun getItemCount(): Int = flights.size

    inner class FlightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvDeparture: TextView = itemView.findViewById(R.id.tvDeparture)
        private val tvDestination: TextView = itemView.findViewById(R.id.tvDestination)
        private val btnFavorite: ImageButton = itemView.findViewById(R.id.btnFavorite)

        fun bind(flight: Flight) {
            tvDeparture.text = flight.departure
            tvDestination.text = flight.destination
            btnFavorite.setOnClickListener { onFavoriteSelected(flight.departure, flight.destination) }
        }
    }
}
