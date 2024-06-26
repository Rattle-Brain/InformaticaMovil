package es.imovil.fiestasasturias.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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

class DetailFragment: Fragment() {

    private val args: DetailFragmentArgs by navArgs()

    private lateinit var fiestaNombre: String

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var rvSlide: RecyclerView

    private lateinit var geoloc: GeoLoc

    private val detailVM: FiestasDetailsViewModel by viewModels() {
        FiestasViewModelFactory((activity?.application as App).fiestaRepo)
    }

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

    fun bindFiestaDetails(){
        detailVM.fiesta.observe(viewLifecycleOwner) { f ->
            with(binding) {

                fNombreDetail.text = f.nombre
                fLocalidadDetail.text = f.localidad
                fMunicipioDetail.text = f.municipio
                fDescDetail.text = f.descripcion
                fZona.text = f.zona
                fDias.text = f.dias

                try {
                    val cords: List<String> = f.coordenadas.split(",")
                    geoloc = GeoLoc(f.nombre,cords[0].toDouble(), cords[1].toDouble())
                    fCoordinates.text = f.coordenadas
                } catch (_: Exception) {
                    fCoordinates.isVisible = false
                }

                fCoordinates.setOnClickListener {
                    /*
                    findNavController().navigate(
                        es.imovil.fiestasasturias.view.FiestasListFragmentDirections.(
                            locationCoordinates
                        )
                    )
                     */
                }

                val slides: List<String> = f.slide.split(",")
                val title = f.slideTitulo

                rvSlide = rSlideDetails

                rvSlide.layoutManager = LinearLayoutManager(context,
                    androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL,false);

                rvSlide.adapter = ImgSliderAdapter(slides, title)
            }
        }

    }
}

