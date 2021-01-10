package pl.futuredev.capstoneproject.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import pl.futuredev.capstoneproject.R
import pl.futuredev.capstoneproject.databinding.FragmentCitiesBinding
import pl.futuredev.capstoneproject.ui.adapters.CitiesAdapter
import javax.inject.Inject

@AndroidEntryPoint
class CitiesFragment : Fragment(R.layout.fragment_cities) {

    private var _binding: FragmentCitiesBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var glide: RequestManager

    private lateinit var citiesAdapter: CitiesAdapter

    private val args: CitiesFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        citiesAdapter.setOnItemClickListener {
            findNavController().navigate(
                CitiesFragmentDirections.actionCitiesFragmentToCityFragment(
                    it.id,
                    it.snippet,
                    it.name,
                    it.images.toTypedArray()
                )
            )
        }
    }

    private fun setupRecyclerView() = binding.rvCities.apply {
        citiesAdapter = CitiesAdapter(glide, args.results.toList())
        adapter = citiesAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}