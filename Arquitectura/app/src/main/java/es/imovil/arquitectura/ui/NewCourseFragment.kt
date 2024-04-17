package es.imovil.arquitectura.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import es.imovil.arquitectura.model.Course
import es.imovil.arquitectura.CourseApp
import es.imovil.arquitectura.R
import es.imovil.arquitectura.databinding.FragmentNewCourseBinding
import es.imovil.arquitectura.domain.CourseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NewCourseFragment : Fragment() {

    private var _binding: FragmentNewCourseBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val courseViewModel: CourseViewModel by viewModels {
        CourseViewModel.CourseViewModelFactory((activity?.application as CourseApp).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNewCourseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGuardar.setOnClickListener {
            Log.d("NewCourseFragment", "Click en guardar")

            val course = getData()
            if (course != null)
            {
                CoroutineScope(Dispatchers.IO).launch {
                    courseViewModel.repository.insertCourse(course)
                }
                findNavController().navigate(R.id.action_CourseFragment_to_ListFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getData(): Course? {
        val nombre = binding.nombreAsignatura.text.toString()
        val profesor = binding.datosProfesor.text.toString()
        val descripcion = binding.descAsignatura.text.toString()

        if (nombre.isEmpty() || profesor.isEmpty() || descripcion.isEmpty())
        {
            Log.d("NewCourseFragment", "El nombre no puede estar vacío")
            return null
        }

        val course: Course = Course(nombre, profesor, descripcion)
        return course
    }
}