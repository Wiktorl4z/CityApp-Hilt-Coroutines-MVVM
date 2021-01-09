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
import kotlinx.android.synthetic.main.fragment_main.*
import pl.futuredev.capstoneproject.R
import pl.futuredev.capstoneproject.others.EventObserver
import pl.futuredev.capstoneproject.ui.viewmodels.MainViewModel
import pl.futuredev.capstoneproject.ui.snackbar

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkBox.setOnClickListener {}
        btGPS.setOnClickListener {}
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                val providedCity = searchView.query.toString()
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
                    progressBar.visibility = View.INVISIBLE
                    snackbar(it)
                },
                onLoading = { progressBar.visibility = View.VISIBLE },

                onSuccess = {
                    progressBar.visibility = View.INVISIBLE
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

    private fun logout(){
        FirebaseAuth.getInstance().signOut()
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.mainFragment, true)
            .build()
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToAuthFragment(),
            navOptions
        )
    }

}


