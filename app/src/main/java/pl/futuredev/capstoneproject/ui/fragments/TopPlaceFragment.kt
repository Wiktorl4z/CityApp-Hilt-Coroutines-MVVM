package pl.futuredev.capstoneproject.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.progressBar
import kotlinx.android.synthetic.main.fragment_top_place.*
import pl.futuredev.capstoneproject.R
import pl.futuredev.capstoneproject.data.remote.entities.Result
import pl.futuredev.capstoneproject.others.EventObserver
import pl.futuredev.capstoneproject.ui.adapters.TopPlacesAdapter
import pl.futuredev.capstoneproject.ui.viewmodels.TopPlaceViewModel
import javax.inject.Inject

@AndroidEntryPoint
class TopPlaceFragment : Fragment(R.layout.fragment_top_place) {

    private val args: TopPlaceFragmentArgs by navArgs()

    private val viewModel: TopPlaceViewModel by viewModels()

    private lateinit var topPlacesAdapter: TopPlacesAdapter

    private var resultList: List<Result>? = null

    @Inject
    lateinit var glide: RequestManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getValue(args.type, args.city)
        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        viewModel.city.observe(
            viewLifecycleOwner, EventObserver(
                onError = {
                    progressBar.visibility = View.INVISIBLE
                },
                onLoading = {
                    progressBar.visibility = View.VISIBLE },

                onSuccess = {
                    ivNoCity.visibility = View.INVISIBLE
                    tvNoCity.visibility = View.INVISIBLE
                    progressBar.visibility = View.INVISIBLE
                    resultList = it
                    setupRecyclerView(it)
                })
        )
    }

    private fun setupRecyclerView(result: List<Result>) = rvPlaces.apply {
        if (result.isNotEmpty()) {
            topPlacesAdapter = TopPlacesAdapter(glide, result)
            adapter = topPlacesAdapter
            layoutManager = LinearLayoutManager(requireContext())

            topPlacesAdapter.setOnItemClickListener { position ->
                resultList?.let {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(resultList!![position].images[0].ownerUrl)
                    )
                    if (intent.resolveActivity(requireActivity().packageManager) != null) {
                        startActivity(intent)
                    }
                }
            }
        }
    }
}