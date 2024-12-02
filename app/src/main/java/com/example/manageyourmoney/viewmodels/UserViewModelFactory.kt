package com.example.manageyourmoney.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.manageyourmoney.repository.AuthRepository

class UserViewModelFactory(
    private val authRepository: AuthRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewmodel::class.java)) {
            return UserViewmodel(authRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
