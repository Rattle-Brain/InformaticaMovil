package es.imovil.fiestasasturias.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import es.imovil.fiestasasturias.databinding.FragmentFiestasBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import es.imovil.fiestasasturias.R

class FiestasFragment : Fragment() {

    private var _binding: FragmentFiestasBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainFargment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // init de las referencias al navHostFragment
        mainFargment = requireActivity().supportFragmentManager.findFragmentById(R.id.mainNavFragment) as NavHostFragment
        navController = mainFargment.navController
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFiestasBinding.inflate(inflater, container, false)
        return binding.root
    }
}