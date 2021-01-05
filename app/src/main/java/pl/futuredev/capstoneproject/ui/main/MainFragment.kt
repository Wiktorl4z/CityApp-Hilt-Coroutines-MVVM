package pl.futuredev.capstoneproject.ui.main

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import pl.futuredev.capstoneproject.R
import pl.futuredev.capstoneproject.others.EventObserver
import pl.futuredev.capstoneproject.ui.snackbar

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()

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
                    snackbar(it.toString())
                }
            )
        )
    }

/*    private fun getProvidedCity(city: String) {
        disposables.add(triposoService.getCityByLocationId("trigram:$city")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response: Recipe? -> this.responseForProvidedCity(response) }) { throwable: Throwable? ->
                this.handleError(
                    throwable
                )
            })
    }

    private fun responseForProvidedCity(response: Recipe) {
        resultList = response.getResults()
        startActivity(resultList)
    }*/

}


