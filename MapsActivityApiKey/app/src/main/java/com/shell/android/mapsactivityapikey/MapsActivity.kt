package com.shell.android.mapsactivityapikey

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnStreetViewPanoramaReadyCallback {

    private lateinit var panorama: StreetViewPanorama

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = fragmentManager.findFragmentById(R.id.streetviewpanorama)
        (mapFragment as StreetViewPanoramaFragment).getStreetViewPanoramaAsync(this)
    }



    override fun onStreetViewPanoramaReady(streetViewPanorama: StreetViewPanorama) {
        panorama = streetViewPanorama
        panorama.setPosition(LatLng(-33.87365, 151.20689))

    }
}
