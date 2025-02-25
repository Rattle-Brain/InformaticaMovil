package es.imovil.persistencia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import es.imovil.practicapersistencia.R
import es.imovil.practicapersistencia.databinding.FragmentDataEntryBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DataEntryFragment : Fragment() {

    private var _binding: FragmentDataEntryBinding? = null
    private val bookViewModel:BookViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDataEntryBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_Entry_to_List)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}