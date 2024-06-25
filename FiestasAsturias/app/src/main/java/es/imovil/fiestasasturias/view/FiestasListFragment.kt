package es.imovil.fiestasasturias.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import es.imovil.fiestasasturias.App
import es.imovil.fiestasasturias.R
import es.imovil.fiestasasturias.adapter.FiestasAdapter
import es.imovil.fiestasasturias.controller.ClickListenerController
import es.imovil.fiestasasturias.databinding.FragmentFiestasListBinding
import es.imovil.fiestasasturias.domain.FiestasViewModel
import es.imovil.fiestasasturias.domain.FiestasViewModelFactory
import es.imovil.fiestasasturias.ui.FiestasUIState

class FiestasListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener,
    ClickListenerController{

    private var _binding: FragmentFiestasListBinding? = null
    private lateinit var binding: FragmentFiestasListBinding
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

        binding = FragmentFiestasListBinding.inflate(layoutInflater)
        mainFargment = requireActivity().supportFragmentManager.findFragmentById(R.id.mainNavFragment) as NavHostFragment
        navController = mainFargment.navController

        fiestasVM.fiestasUIStateObservable.observe(this) { result ->
            when (result) {
                is FiestasUIState.Success -> {
                    fAdapter.submitList(result.fiestas.fiestas)
                    binding.swipeRefreshLayout.isRefreshing = false
                }
                is FiestasUIState.Error -> {
                    Snackbar.make(binding.root, result.message, Snackbar.LENGTH_SHORT).show()
                    binding.swipeRefreshLayout.isRefreshing = false
                }
                is FiestasUIState.Loading -> binding.swipeRefreshLayout.isRefreshing = true
            }
        }
        //binding.swipeRefreshLayout.setOnRefreshListener(refreshListener)


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

        fAdapter = FiestasAdapter(this)
        rvFiestas.adapter = fAdapter
        rvFiestas.setHasFixedSize(true)

        
        srLayout = binding.swipeRefreshLayout
        srLayout.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        fiestasVM.getFiestasList()
    }

    override fun onItemClick(position: Int) {
        val nombreFiesta: String = fiestasVM.fiestasFiltradas.value?.get(position)?.nombre ?: ""
        if (nombreFiesta == "") return
        findNavController().navigate(
            FiestasListFragmentDirections.actionFiestasFragmentToDetailFragment(nombreFiesta)
        )
    }


}
