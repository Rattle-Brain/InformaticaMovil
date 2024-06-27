package es.imovil.fiestasasturias.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.imovil.fiestasasturias.App
import es.imovil.fiestasasturias.adapter.ImgSliderAdapter
import es.imovil.fiestasasturias.data.GeoLoc
import es.imovil.fiestasasturias.databinding.FragmentDetailBinding
import es.imovil.fiestasasturias.domain.FiestasDetailsViewModel
import es.imovil.fiestasasturias.domain.FiestasViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()
    private val detailVM: FiestasDetailsViewModel by viewModels {
        FiestasViewModelFactory((activity?.application as App).fiestaRepo)
    }
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var fiestaNombre: String
    private lateinit var geoloc: GeoLoc
    private lateinit var rvSlide: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            fiestaNombre = args.fNombre
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailVM.setFiesta(fiestaNombre)
        bindFiestaDetails()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun bindFiestaDetails() {
        detailVM.fiesta.observe(viewLifecycleOwner) { fiesta ->
            binding.apply {
                fNombreDetail.text = fiesta.nombre
                fLocalidadDetail.text = fiesta.localidad
                fMunicipioDetail.text = fiesta.municipio
                fDescDetail.text = fiesta.descripcion
                fZona.text = fiesta.zona
                fDias.text = fiesta.dias

                try {
                    val coords = fiesta.coordenadas.split(",")
                    geoloc = GeoLoc(fiesta.nombre, coords[0].toDouble(), coords[1].toDouble())
                    fCoordinates.text = fiesta.coordenadas
                } catch (e: Exception) {
                    fCoordinates.isVisible = false
                }

                fCoordinates.setOnClickListener {
                    findNavController().navigate(
                        DetailFragmentDirections.actionDetailFragmentToMapFragment(geoloc)
                    )
                }

                rvSlide = rSlideDetails

                rvSlide.apply {
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = ImgSliderAdapter(fiesta.slide.split(","), fiesta.slideTitulo)
                }
            }
        }
    }
}
