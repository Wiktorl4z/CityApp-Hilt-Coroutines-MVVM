package pl.futuredev.capstoneproject.ui.main

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_main.*
import pl.futuredev.capstoneproject.R
import pl.futuredev.capstoneproject.ui.snackbar

class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkBox.setOnClickListener {}
        btGPS.setOnClickListener {}
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                val providedCity = searchView.query.toString()
                if (!providedCity.matches("[a-zA-Z]+".toRegex())) {
                    snackbar(getString(R.string.contain_only_words))
                } else {
                    snackbar("Working fine")
                    // getProvidedCity()
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
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


