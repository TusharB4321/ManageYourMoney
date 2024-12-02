package com.example.manageyourmoney.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.manageyourmoney.repository.AuthRepository
import com.example.manageyourmoney.utils.Resources

class SignUpViewmodel(private val authRepository: AuthRepository):ViewModel()
{
    private val _signupStatus=MutableLiveData<Resources<String>>()
    val signupStatus:LiveData<Resources<String>> =_signupStatus

    fun registerUser(email:String,password:String,confirmPassword:String){
        _signupStatus.value=Resources.Loading()
        authRepository.registerUser(email,password,confirmPassword){resources ->
            _signupStatus.postValue(resources)

        }
    }
}