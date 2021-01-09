package pl.futuredev.capstoneproject.ui.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import pl.futuredev.capstoneproject.MainActivity
import pl.futuredev.capstoneproject.R
import pl.futuredev.capstoneproject.ui.snackbar

@AndroidEntryPoint
class AuthFragment : Fragment(R.layout.fragment_auth) {

    companion object {
        const val SIGN_CODE = 1001
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isLoggedIn()) {
            redirectLogin()
        } else {
            launchSignIn()
        }
    }

    private fun launchSignIn() {
        val authProviders = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(
                authProviders
            ).build(), SIGN_CODE
        )
    }

    private fun isLoggedIn(): Boolean {
        FirebaseAuth.getInstance().currentUser?.let {
            return true
        } ?: return false
    }

    private fun redirectLogin() {
        val navOptions = NavOptions.Builder().setPopUpTo(R.id.authFragment, true)
            .build()
        findNavController().navigate(
            AuthFragmentDirections.actionAuthFragmentToMainFragment(),
            navOptions
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SIGN_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Intent(requireContext(), MainActivity::class.java).also {
                    startActivity(it)
                    requireActivity().finish()
                }
            } else {
                snackbar("Sign in unsuccessful")
            }
        }
    }

}