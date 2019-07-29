package com.shell.android.mapsactivityapikey

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMapClickListener,
    GoogleMap.OnMarkerDragListener {
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Click sobre el mapa
        mMap.setOnMapClickListener(this)

        // Eventos en los markers
        mMap.setOnMarkerDragListener(this)

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        val sydneyMarker = mMap.addMarker(MarkerOptions()
            .position(sydney)
            .title("Marker in Sydney")
            .snippet("Ahora mismo no estamos aqui")
            .draggable(true)
//            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_pin))
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        // Aplicando estilo al mapa
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            val success = mMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json)
            )
            if (!success) {
                Log.e("TAG", "Style parsin failed")
            }
        } catch (e : Resources.NotFoundException) {
            Log.e("TAG", "Can't find style. Error: $e")
        }
    }

    override fun onMapClick(pos: LatLng) {
        mMap.addMarker(MarkerOptions().position(pos)
            .title("Nuevo Marker")
            .draggable(true))
        mMap.animateCamera(CameraUpdateFactory.newLatLng(pos))
    }

    override fun onMarkerDragStart(marker: Marker) {
        marker.hideInfoWindow()
    }

    override fun onMarkerDrag(marker: Marker) {
    }

    override fun onMarkerDragEnd(marker: Marker) {
        marker.apply {
            snippet = "${position.latitude}, ${position.longitude}"
            showInfoWindow()
        }
    }
}
