package ar.edu.utn.frba.mobile.plantscare.ui.main.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class LoginScreenViewModel: ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)

    fun signInWithGoogleCredentials(credentials: AuthCredential, home: () -> Unit)
    = viewModelScope.launch {
        try {
            auth.signInWithCredential(credentials)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful) {
                        Log.d("PlantsCare", "Logged in with google successfully")
                        home()
                    }
                }
                .addOnFailureListener {
                    Log.d("PlantsCare", "Logged in failed")
                }
        } catch (e: Exception) {
            Log.d("PlantsCare", "Error Google. ${e.localizedMessage}")
        }
    }
}