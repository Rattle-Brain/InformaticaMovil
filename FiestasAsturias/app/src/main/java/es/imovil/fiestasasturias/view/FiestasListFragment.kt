package es.imovil.fiestasasturias.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import es.imovil.fiestasasturias.App
import es.imovil.fiestasasturias.R
import es.imovil.fiestasasturias.adapter.FiestasAdapter
import es.imovil.fiestasasturias.databinding.FragmentFiestasListBinding
import es.imovil.fiestasasturias.domain.FiestasViewModel
import es.imovil.fiestasasturias.domain.FiestasViewModelFactory

class FiestasListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private var _binding: FragmentFiestasListBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainFargment: NavHostFragment
    private lateinit var navController: NavController

    private lateinit var rvFiestas: RecyclerView
    lateinit var srLayout: SwipeRefreshLayout
    private lateinit var fAdapter: FiestasAdapter

    private val fiestasVM: FiestasViewModel by viewModels {
        FiestasViewModelFactory((activity?.application as App).fiestaRepo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainFargment = requireActivity().supportFragmentManager.findFragmentById(R.id.mainNavFragment) as NavHostFragment
        navController = mainFargment.navController
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFiestasListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvFiestas = binding.fiestasList
        rvFiestas.layoutManager = LinearLayoutManager(this.context)

        fAdapter = FiestasAdapter()
        rvFiestas.adapter = fAdapter
        rvFiestas.setHasFixedSize(true)

        
        srLayout = binding.swipeRefreshLayout
        srLayout.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        fiestasVM.getFiestasList()
    }

}
