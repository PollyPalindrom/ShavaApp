package com.example.shavaapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shavaapp.common.EntryListener
import com.example.shavaapp.databinding.MainActivityBinding
import com.example.shavaapp.presentation.entry.EntryFragment
import com.example.shavaapp.presentation.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), EntryListener {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) openEntryFragment()
    }

    override fun openEntryFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.fContainerView.id, EntryFragment()).commit()
    }

    override fun openMainFragment() {
        val mainFragment = MainFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.fContainerView.id, mainFragment).commit()
    }
}