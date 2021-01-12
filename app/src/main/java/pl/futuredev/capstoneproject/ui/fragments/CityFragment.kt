package pl.futuredev.capstoneproject.ui.fragments

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import pl.futuredev.capstoneproject.R
import pl.futuredev.capstoneproject.data.local.entities.City
import pl.futuredev.capstoneproject.databinding.CityContentBinding
import pl.futuredev.capstoneproject.databinding.FragmentCityBinding
import pl.futuredev.capstoneproject.others.Constants
import pl.futuredev.capstoneproject.ui.viewmodels.CityViewModel
import javax.inject.Inject

@AndroidEntryPoint
class CityFragment : Fragment(R.layout.fragment_city) {

    private val args: CityFragmentArgs by navArgs()

    private var _binding: FragmentCityBinding? = null
    private lateinit var content: CityContentBinding

    private val viewModel: CityViewModel by viewModels()

    private val binding get() = _binding!!

    private var isLiked = false

    @Inject
    lateinit var glide: RequestManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCityBinding.inflate(inflater, container, false)
        content = CityContentBinding.bind(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()

        subscribeToObservers()


        content.btFood.setOnClickListener {
            findNavController().navigate(
                CityFragmentDirections.actionCityFragmentToTopPlaceFragment(
                    args.id,
                    Constants.PLACES_EAT
                )
            )
        }
        content.btSightseeing.setOnClickListener {
            findNavController().navigate(
                CityFragmentDirections.actionCityFragmentToTopPlaceFragment(
                    args.id,
                    Constants.PLACES_TO_SEE
                )
            )
        }
        content.btTour.setOnClickListener {
            findNavController().navigate(
                CityFragmentDirections.actionCityFragmentToTopPlaceFragment(
                    args.id,
                    Constants.SCORING
                )
            )
        }
        binding.fab.setOnClickListener {
            if (isLiked) {
                viewModel.removeFromLikedCity(args.name)
            } else {
                viewModel.likeCity(
                    City(
                        args.name,
                        args.snippet,
                        args.image.toList(),
                        args.id
                    )
                )
            }
        }
    }

    private fun subscribeToObservers() {
        viewModel.isLikedCity(args.name)?.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                isLiked = true
                binding.fab.backgroundTintList = ColorStateList.valueOf(
                    Color.parseColor("#087f23")
                )
            } else {
                isLiked = false
                binding.fab.backgroundTintList = ColorStateList.valueOf(
                    Color.parseColor("#c2c1c1")
                )
            }
        })
    }

    private fun setUpViews() {
        content.tvSnippet.text = args.snippet
        content.tvName.text = args.name
        glide.load(args.image[0].sizes.original.url).into(binding.threeTwoImage)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}