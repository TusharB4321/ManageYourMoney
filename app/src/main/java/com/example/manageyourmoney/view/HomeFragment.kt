package com.example.manageyourmoney.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.manageyourmoney.R
import com.example.manageyourmoney.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClicklistenersOnViews()
    }

    private fun setClicklistenersOnViews() {

        binding.btnHomeAddTransaction.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_selectTransactionFragment2)
        }
        binding.btnHomeMyBalance.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_allTransactionsFragment)

        }
        binding.tvHomeSeeAll.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_allTransactionsFragment)

        }
        binding.ivHomeUserImage.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_profileFragment)

        }
    }
}