package com.example.manageyourmoney.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.manageyourmoney.repository.AuthRepository
import com.example.manageyourmoney.utils.Resources

class UserViewmodel(private val authRepository: AuthRepository):ViewModel()
{
    private val _signupStatus=MutableLiveData<Resources<String>>()
    private val _signInStatus=MutableLiveData<Resources<String>>()
    val signupStatus:LiveData<Resources<String>> =_signupStatus
    val signInStatus:LiveData<Resources<String>> =_signInStatus

    fun registerUser(email:String,password:String,confirmPassword:String){
        _signupStatus.value=Resources.Loading()
        authRepository.registerUser(email,password,confirmPassword){resources ->
            _signupStatus.postValue(resources)

        }
    }

    fun signInUser(email: String,password: String){
        _signInStatus.value=Resources.Loading()
        authRepository.signInUser(email,password){resources ->
            _signInStatus.postValue(resources)
        }
    }
}