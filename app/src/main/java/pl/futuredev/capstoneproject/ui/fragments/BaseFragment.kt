package pl.futuredev.capstoneproject.ui.fragments

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment(layout: Int) : Fragment(layout) {

    fun showSnackbar(text: String) {
      /*  Snackbar.make(
            requireActivity().rootLayout,
            text,
            Snackbar.LENGTH_LONG
        ).show()*/
    }

}