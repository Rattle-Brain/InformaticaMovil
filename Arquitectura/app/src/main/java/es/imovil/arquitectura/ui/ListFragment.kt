package es.imovil.arquitectura.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.imovil.arquitectura.CourseApp
import es.imovil.arquitectura.R
import es.imovil.arquitectura.adapters.CourseListAdapter
import es.imovil.arquitectura.databinding.FragmentListBinding
import es.imovil.arquitectura.domain.CourseViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val courseViewModel: CourseViewModel by viewModels {
        CourseViewModel.CourseViewModelFactory((activity?.application as CourseApp).repository)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): RecyclerView? {

        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewRoot = binding.root

        courseViewModel.courseNames.observe(viewLifecycleOwner) { names ->
            names.let {
                val adapter = CourseListAdapter()
                adapter.submitList(names.toMutableList())
            }
        }

        viewRoot.layoutManager = LinearLayoutManager(viewRoot.context)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_ListFragment_to_CourseFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}