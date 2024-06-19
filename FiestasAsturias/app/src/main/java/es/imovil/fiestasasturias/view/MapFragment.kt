package es.imovil.fiestasasturias.view

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import es.imovil.fiestasasturias.R
import es.imovil.fiestasasturias.databinding.FragmentMapBinding
import org.osmdroid.api.IMapController
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class MapFragment: Fragment() {

    val args: MapFragmentArgs by navArgs()      // Argumentos (SafeArgs)

    // Binding
    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    lateinit var map: MapView                   // Mapa
    lateinit var mapController: IMapController  // Controlador
    lateinit var marker: Marker                 // Marcador
    lateinit var locationPoint: GeoPoint        // Coordenadas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Configuration.getInstance().load(requireContext(), PreferenceManager.getDefaultSharedPreferences(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Instanciar mapa y controlador
        map = binding.mapView
        map.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE)
        mapController = map.controller

        // Tomamos los argumentos de SafeArgs
        val latitude: Double = args.coordenadas.latitud
        val longitude: Double = args.coordenadas.longitud

        // Nos movemos a las coordenadas del establecimiento
        locationPoint = GeoPoint(latitude, longitude)
        mapController.zoomTo(16,2)
        mapController.animateTo(locationPoint)

        // Preparacion del marcador del establecimiento
        marker = Marker(map)
        marker.position = locationPoint
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        marker.title = args.coordenadas.fNombre

        // Pintamos el marcador
        map.overlays.add(marker)

        // Modificamos la toolbar según las necesidades de esta vista
        initToolbar()
    }

    /**
     * Inicializa una toolbar muy sencilla con una flecha de retorno a la lista de establecimientos
     */
    private fun initToolbar() {
        val mainActivity: MainActivity = requireActivity() as MainActivity
        mainActivity.setSupportActionBar(binding.toolbar)

        val supportActionBar = (requireActivity() as MainActivity).supportActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }
}