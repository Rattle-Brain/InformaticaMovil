package es.imovil.fiestasasturias.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
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
    ClickListenerController, SearchView.OnQueryTextListener {

    private var _binding: FragmentFiestasListBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    private val fiestasVM: FiestasViewModel by viewModels {
        FiestasViewModelFactory((requireActivity().application as App).fiestaRepo)
    }

    private lateinit var fAdapter: FiestasAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFiestasListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSwipeRefreshLayout()
        createToolBar()
        observeViewModel()

        binding.swipeRefreshLayout.setOnRefreshListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        binding.fiestasList.apply {
            layoutManager = LinearLayoutManager(context)
            fAdapter = FiestasAdapter(this@FiestasListFragment)
            adapter = fAdapter
            setHasFixedSize(true)
        }
    }

    private fun setupSwipeRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            fiestasVM.getFiestasList()
        }
    }

    private fun observeViewModel() {
        fiestasVM.fiestasUIStateObservable.observe(viewLifecycleOwner) { result ->
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

        fiestasVM.fiestasFiltradas.observe(viewLifecycleOwner) { fiestasFiltradasList ->
            fAdapter.submitList(fiestasFiltradasList)
        }
    }

    override fun onRefresh() {
        fiestasVM.getFiestasList()
    }

    override fun onItemClick(position: Int) {
        val nombreFiesta = fiestasVM.fiestasFiltradas.value?.get(position)?.nombre ?: return
        findNavController().navigate(
            FiestasListFragmentDirections.actionFiestasFragmentToDetailFragment(nombreFiesta)
        )
    }

    private fun createToolBar() {
        val mainActivity = requireActivity() as MainActivity
        mainActivity.addMenuProvider(MenuProviderImpl(), viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {

        if (p0 != null)
            fiestasVM.query.value = p0
        else
            fiestasVM.query.value = ""

        return true
    }

    private inner class MenuProviderImpl : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(R.menu.menu_main_activity, menu)
            menu.findItem(R.id.busqueda)?.let { menuItem ->
                (menuItem.actionView as? SearchView)?.apply {
                    setQuery(fiestasVM.query.value, false)
                    setOnQueryTextListener(this@FiestasListFragment)
                }
            }
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            return when (menuItem.itemId) {
                R.id.ajustes -> false
                else -> false
            }
        }
    }
}
