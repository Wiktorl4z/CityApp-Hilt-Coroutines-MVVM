package pl.futuredev.capstoneproject.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import pl.futuredev.capstoneproject.R
import pl.futuredev.capstoneproject.databinding.FragmentMainBinding
import pl.futuredev.capstoneproject.others.EventObserver
import pl.futuredev.capstoneproject.ui.snackbar
import pl.futuredev.capstoneproject.ui.viewmodels.MainViewModel

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.checkBox.setOnClickListener {}
        binding.btGPS.setOnClickListener {}
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                val providedCity = binding.searchView.query.toString()
                if (!providedCity.matches("[a-zA-Z]+".toRegex())) {
                    snackbar((getString(R.string.contain_only_words)))
                } else {
                    viewModel.getCitiesByName(providedCity)
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        viewModel.cities.observe(
            viewLifecycleOwner, EventObserver(
                onError = {
                    binding.progressBar.visibility = View.INVISIBLE
                    snackbar(it)
                },
                onLoading = { binding.progressBar.visibility = View.VISIBLE },

                onSuccess = {
                    binding.progressBar.visibility = View.INVISIBLE
                    findNavController().navigate(
                        MainFragmentDirections.actionMainFragmentToCitySearchResultFragment(it.toTypedArray())
                    )
                }
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favCity -> {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToFavFragment()
                )
            }
            R.id.signOut -> logout()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.mainFragment, true)
            .build()
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToAuthFragment(),
            navOptions
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


