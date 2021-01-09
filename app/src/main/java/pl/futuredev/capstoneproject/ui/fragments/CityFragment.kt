package pl.futuredev.capstoneproject.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.city_content.*
import kotlinx.android.synthetic.main.fragment_city.*
import pl.futuredev.capstoneproject.R
import pl.futuredev.capstoneproject.others.Constants
import javax.inject.Inject

@AndroidEntryPoint
class CityFragment : Fragment(R.layout.fragment_city) {

    private val args: CityFragmentArgs by navArgs()

    @Inject
    lateinit var glide: RequestManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()

        btFood.setOnClickListener {
            findNavController().navigate(
                CityFragmentDirections.actionCityFragmentToTopPlaceFragment(
                    args.id,
                    Constants.PLACES_EAT
                )
            )
        }
        btSightseeing.setOnClickListener {
            findNavController().navigate(
                CityFragmentDirections.actionCityFragmentToTopPlaceFragment(
                    args.id,
                    Constants.PLACES_TO_SEE
                )
            )
        }
        btTour.setOnClickListener {
            findNavController().navigate(
                CityFragmentDirections.actionCityFragmentToTopPlaceFragment(
                    args.id,
                    Constants.SCORING
                )
            )
        }

    }

    private fun setUpViews() {
        tvSnippet.text = args.snippet
        tvName.text = args.name
        glide.load(args.images[0].sizes.original.url).into(threeTwoImage)
    }
}