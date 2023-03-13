package com.example.jetareader.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginScreenViewModel : ViewModel() {
    // val loadingState = MutableStateFlow(LoadingState.IDLE)
    private val auth: FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit)
        = viewModelScope.launch {
            try {
                Log.d("FB", "signInWithEmailAndPassword: Try statement")
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful) {
                            Log.d("FB", "signInWithEmailAndPassword success: ${task.result.toString()}")
                            home()
                        } else {
                            Log.d("FB", "signInWithEmailAndPassword fail: ${task.result.toString()}")
                        }
                    }
            } catch(ex: Exception) {
                Log.d("FB", "signInWithEmailAndPassword exception")
            }
    }

    fun createUserWithEmailAndPassword(email: String, password: String, home: () -> Unit) {
        if (_loading.value == false) {
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        // me@gmail.com
                        val displayName = task.result?.user?.email?.split('@')?.get(0)
                        createUser(displayName)
                        home()
                    } else {
                        Log.d("FB", "createUserWithEmailAndPassword exception")
                    }
                    _loading.value = false
                }
        }
    }

    private fun createUser(displayName: String?) {

    }
}