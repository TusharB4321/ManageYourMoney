package com.example.manageyourmoney.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.manageyourmoney.databinding.FragmentSignUpBinding
import com.example.manageyourmoney.repository.AuthRepository
import com.example.manageyourmoney.utils.Resources
import com.example.manageyourmoney.viewmodels.SignUpViewModelFactory
import com.example.manageyourmoney.viewmodels.SignUpViewmodel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUpFragment : Fragment() {

    private lateinit var binding:FragmentSignUpBinding
    private lateinit var authRepository: AuthRepository
    private val viewmodel:SignUpViewmodel by viewModels{
        SignUpViewModelFactory(authRepository)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentSignUpBinding.inflate(layoutInflater)
        val firebaseAuth = FirebaseAuth.getInstance()
        val databaseReference = FirebaseDatabase.getInstance().reference
        // Initialize AuthRepository
        authRepository = AuthRepository(firebaseAuth, databaseReference)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignupApply.setOnClickListener{
            val email=binding.etSignupEmail.text.toString()
            val password=binding.etSignupPassword.text.toString()
            val confirmPassword=binding.etSignupConfirmPassword.text.toString()

            viewmodel.registerUser(email, password, confirmPassword)
        }

        viewmodel.signupStatus.observe(viewLifecycleOwner, Observer { resources->

            when(resources){
                is Resources.Loading->{
                    binding.progressBar.visibility=View.VISIBLE
                }
                is Resources.Success->{
                    binding.progressBar.visibility=View.GONE
                    Toast.makeText(context, resources.data, Toast.LENGTH_SHORT).show()
                }
                is Resources.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, resources.message, Toast.LENGTH_SHORT).show()
                }
            }

        })
    }



}