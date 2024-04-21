package es.imovil.arquitectura.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import es.imovil.arquitectura.ASIGNATURA
import es.imovil.arquitectura.ASIGNATURA
import es.imovil.arquitectura.CourseApp
import es.imovil.arquitectura.R
import es.imovil.arquitectura.databinding.FragmentDetailBinding
import es.imovil.arquitectura.databinding.FragmentNewCourseBinding
import es.imovil.arquitectura.domain.CourseDetailsViewModel
import es.imovil.arquitectura.domain.CourseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val courseDetailsViewModel: CourseDetailsViewModel by viewModels {
        CourseDetailsViewModel.CourseDetailsViewModelFactory((activity?.application as CourseApp).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        courseDetailsViewModel.mCourse.observe(viewLifecycleOwner) { names ->
            names.let {
                binding.asignatura.text = names.name
                binding.profesor.text = names.teacher
                binding.detalles.text = names.description
            }
        }

        arguments?.let {
            val recibido = it.getString(ASIGNATURA)
            Log.d("DetailsFragment", "Recibido: $recibido")
            binding.asignatura.text = recibido

            courseDetailsViewModel.setCourse(recibido!!)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}