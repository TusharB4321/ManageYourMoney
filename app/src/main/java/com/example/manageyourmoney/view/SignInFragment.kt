package com.example.manageyourmoney.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.manageyourmoney.databinding.FragmentSignInBinding
import com.example.manageyourmoney.repository.AuthRepository
import com.example.manageyourmoney.utils.Resources
import com.example.manageyourmoney.viewmodels.UserViewModelFactory
import com.example.manageyourmoney.viewmodels.UserViewmodel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var authRepository: AuthRepository
    private val viewmodel: UserViewmodel by viewModels{
        UserViewModelFactory(authRepository)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSignInBinding.inflate(layoutInflater)
        val firebaseAuth = FirebaseAuth.getInstance()
        val databaseReference = FirebaseDatabase.getInstance().reference
        authRepository=AuthRepository(firebaseAuth,databaseReference)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signInUser()

    }

    private fun signInUser() {

        binding.btnLoginApply.setOnClickListener {
            val email=binding.etLoginEmail.text.toString()
            val password=binding.etLoginPassword.text.toString()

            viewmodel.signInUser(email,password)
        }

        viewmodel.signInStatus.observe(viewLifecycleOwner, Observer { resources->

            when(resources){
                is Resources.Loading->{
                  binding.progressBar.visibility=View.VISIBLE
                }
                is Resources.Success->{
                    binding.progressBar.visibility=View.GONE
                    Toast.makeText(context, resources.data, Toast.LENGTH_SHORT).show()
                }
                is Resources.Error->{
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, resources.message, Toast.LENGTH_SHORT).show()
                }
            }

        })
    }
}