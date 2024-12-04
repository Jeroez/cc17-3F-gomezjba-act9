package com.example.flightsearch

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: FlightViewModel
    private lateinit var airportAdapter: AirportAdapter
    private lateinit var flightAdapter: FlightAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[FlightViewModel::class.java]

        val searchField: EditText = findViewById(R.id.etSearch)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        airportAdapter = AirportAdapter { airportCode ->
            fetchFlights(airportCode)
        }
        flightAdapter = FlightAdapter { departure, destination ->
            saveFavorite(departure, destination)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = airportAdapter

        searchField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                fetchAirports(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun fetchAirports(query: String) {
        lifecycleScope.launch {
            val airports = viewModel.getAirports(query)
            airportAdapter.submitList(airports)
        }
    }

    private fun fetchFlights(departureCode: String) {
        lifecycleScope.launch {
            val flights = viewModel.getFlights(departureCode)
            flightAdapter.submitList(flights)
        }
    }

    private fun saveFavorite(departure: String, destination: String) {
        lifecycleScope.launch {
            viewModel.saveFavoriteFlight(departure, destination)
        }
    }
}
