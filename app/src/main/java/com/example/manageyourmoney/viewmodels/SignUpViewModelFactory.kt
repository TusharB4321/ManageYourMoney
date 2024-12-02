package com.example.manageyourmoney.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.manageyourmoney.repository.AuthRepository

class SignUpViewModelFactory(
    private val authRepository: AuthRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewmodel::class.java)) {
            return SignUpViewmodel(authRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
