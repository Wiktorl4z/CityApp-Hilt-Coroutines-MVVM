package pl.futuredev.capstoneproject.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

class LoginViewModel : ViewModel() {

    enum class AuthState {
        AUTHENTICATED, UNAUTHENTICATED
    }

    val authState = FirebaseUserLiveData().map { user ->
        if (user != null) {
            AuthState.AUTHENTICATED
        } else {
            AuthState.UNAUTHENTICATED
        }
    }

    fun login(email: String?, password: String?) {



    }


}