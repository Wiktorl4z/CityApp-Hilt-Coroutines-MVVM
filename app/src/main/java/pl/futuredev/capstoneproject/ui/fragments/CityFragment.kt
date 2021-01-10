package pl.futuredev.capstoneproject.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import pl.futuredev.capstoneproject.R
import pl.futuredev.capstoneproject.databinding.CityContentBinding
import pl.futuredev.capstoneproject.databinding.FragmentCityBinding
import pl.futuredev.capstoneproject.others.Constants
import javax.inject.Inject

@AndroidEntryPoint
class CityFragment : Fragment(R.layout.fragment_city) {

    private val args: CityFragmentArgs by navArgs()

    private var _binding: FragmentCityBinding? = null
    private lateinit var cityContent: CityContentBinding

    private val binding get() = _binding!!

    @Inject
    lateinit var glide: RequestManager


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCityBinding.inflate(inflater, container, false)
        cityContent = CityContentBinding.bind(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()

        cityContent.btFood.setOnClickListener {
            findNavController().navigate(
                CityFragmentDirections.actionCityFragmentToTopPlaceFragment(
                    args.id,
                    Constants.PLACES_EAT
                )
            )
        }
        cityContent.btSightseeing.setOnClickListener {
            findNavController().navigate(
                CityFragmentDirections.actionCityFragmentToTopPlaceFragment(
                    args.id,
                    Constants.PLACES_TO_SEE
                )
            )
        }
        cityContent.btTour.setOnClickListener {
            findNavController().navigate(
                CityFragmentDirections.actionCityFragmentToTopPlaceFragment(
                    args.id,
                    Constants.SCORING
                )
            )
        }

    }

    private fun setUpViews() {
        cityContent.tvSnippet.text = args.snippet
        cityContent.tvName.text = args.name
        glide.load(args.images[0].sizes.original.url).into(binding.threeTwoImage)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}