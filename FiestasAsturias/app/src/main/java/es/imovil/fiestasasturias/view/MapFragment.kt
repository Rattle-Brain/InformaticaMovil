package es.imovil.fiestasasturias.view

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import es.imovil.fiestasasturias.databinding.FragmentMapBinding
import org.osmdroid.api.IMapController
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

/**
 * Fragmento que contiene la logica del mapa en el que se ve la geoloc de la fiesta
 */
class MapFragment : Fragment() {

    private val args: MapFragmentArgs by navArgs()

    // Binding
    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    private lateinit var map: MapView
    private lateinit var controller: IMapController
    private lateinit var marker: Marker
    private lateinit var fiestaGeoPoint: GeoPoint

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Configuration.getInstance().load(requireContext(), PreferenceManager.getDefaultSharedPreferences(requireContext()))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        map = binding.mapView
        map.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE)
        controller = map.controller

        val latitude = args.coordenadas.latitud
        val longitude = args.coordenadas.longitud

        fiestaGeoPoint = GeoPoint(latitude, longitude)
        controller.setZoom(16.0)
        controller.setCenter(fiestaGeoPoint)

        // Creamos el marcador (pin) que indica la localización
        marker = Marker(map)
        marker.position = fiestaGeoPoint
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        marker.title = args.coordenadas.fNombre

        map.overlays.add(marker)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}