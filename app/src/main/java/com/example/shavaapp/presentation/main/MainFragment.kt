package com.example.shavaapp.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.shavaapp.R
import com.example.shavaapp.databinding.MainFragmentBinding
import com.example.shavaapp.presentation.account.AccountFragment
import com.example.shavaapp.presentation.menu.MenuFragment
import com.example.shavaapp.presentation.offers.OffersFragment
import com.example.shavaapp.presentation.shoppingCart.ShoppingCartFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (savedInstanceState == null) openMenuFragment()

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu -> openMenuFragment()
                R.id.offers -> openOffersFragment()
                R.id.account -> openAccountFragment()
                R.id.cart -> openCartFragment()
            }
            true
        }
    }

    private fun openAccountFragment() {
        val recyclerFragment = AccountFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(binding.fContainerView.id, recyclerFragment).commit()
    }

    private fun openCartFragment() {
        val recyclerFragment = ShoppingCartFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(binding.fContainerView.id, recyclerFragment).commit()
    }

    private fun openOffersFragment() {
        val recyclerFragment = OffersFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(binding.fContainerView.id, recyclerFragment).commit()
    }

    private fun openMenuFragment() {
        val recyclerFragment = MenuFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(binding.fContainerView.id, recyclerFragment).commit()
    }

}