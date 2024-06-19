package es.imovil.fiestasasturias.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import es.imovil.fiestasasturias.App
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
    }
}

