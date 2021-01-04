package pl.futuredev.capstoneproject.ui.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_auth.*
import pl.futuredev.capstoneproject.R
import pl.futuredev.capstoneproject.ui.main.MainActivity
import pl.futuredev.capstoneproject.ui.snackbar

@AndroidEntryPoint
class AuthFragment : Fragment(R.layout.fragment_auth) {

    companion object {
        const val SIGN_CODE = 1001
    }

    private val viewModel by viewModels<LoginViewModel>()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
        launchSignIn()
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

    private fun subscribeToObservers() {
/*        viewModel.authState.observe(viewLifecycleOwner, Observer { authState ->
            when (authState) {
                LoginViewModel.AuthState.AUTHENTICATED -> {
                    Intent(requireContext(), MainActivity::class.java).also {
                        startActivity(it)
                        requireActivity().finish()
                    }
                }
                else -> {
                }
            }
        })*/
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