package pl.futuredev.capstoneproject.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import pl.futuredev.capstoneproject.R
import pl.futuredev.capstoneproject.data.local.entities.City
import pl.futuredev.capstoneproject.data.local.viewmodels.CityViewModel
import pl.futuredev.capstoneproject.databinding.FragmentFavBinding
import pl.futuredev.capstoneproject.others.EventObserver
import pl.futuredev.capstoneproject.ui.adapters.CitiesAdapter
import pl.futuredev.capstoneproject.ui.adapters.FavCitiesAdapter
import javax.inject.Inject

@AndroidEntryPoint
class FavFragment : Fragment(R.layout.fragment_fav) {

    private var _binding: FragmentFavBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var glide: RequestManager
    private val viewModel: CityViewModel by viewModels()
    private lateinit var citiesAdapter: FavCitiesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllCities()
        subscribeToObservers()

    }

    private fun subscribeToObservers() {
        viewModel.cities.observe(viewLifecycleOwner, EventObserver(
            onLoading = {
                binding.progressBar.visibility = View.VISIBLE
                binding.tvEmpty.visibility = View.INVISIBLE
                binding.ivEmptyCity.visibility = View.INVISIBLE
            },
            onError = {
                binding.progressBar.visibility = View.INVISIBLE
                binding.tvEmpty.visibility = View.INVISIBLE
                binding.ivEmptyCity.visibility = View.INVISIBLE
                // snackbar
            },
            onSuccess = { cities ->
                if (cities.isNotEmpty()) {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.tvEmpty.visibility = View.INVISIBLE
                    binding.ivEmptyCity.visibility = View.INVISIBLE
                    setupRecyclerView(cities)
                } else {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.tvEmpty.visibility = View.VISIBLE
                    binding.ivEmptyCity.visibility = View.VISIBLE
                }
            }
        ))
    }

    private fun setupRecyclerView(cities: List<City>) = binding.rvCities.apply {
        citiesAdapter = FavCitiesAdapter(glide, cities)
        adapter = citiesAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

}