package com.example.shavaapp.presentation.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shavaapp.R
import com.example.shavaapp.common.FullScreenListener
import com.example.shavaapp.databinding.MenuFragmentBinding
import com.example.shavaapp.presentation.fullScreen.FullScreenFragment
import com.example.shavaapp.presentation.recycler.adapter.MenuAdapter
import com.example.shavaapp.presentation.recycler.adapter.MenuPositionAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : Fragment(), FullScreenListener {

    private val viewModel by viewModels<MenuViewModel>()
    private lateinit var binding: MenuFragmentBinding
    private val menuAdapter = MenuAdapter()
    private val menuPositionAdapter = MenuPositionAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MenuFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerForDiscounts.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = menuAdapter
        }

        binding.recyclerForMenu.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = menuPositionAdapter
        }

        viewModel.positions.observe(viewLifecycleOwner)
        {
            if (!it.isNullOrEmpty()) {
                menuPositionAdapter.submitList(it)
            }

        }
        viewModel.offers.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                menuAdapter.submitList(it)
            }
        }
        viewModel.loadMenuPositions()
        viewModel.loadOffers()
    }

    override fun createFullScreen(url: String, name: String) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fContainerView, FullScreenFragment.newInstance(url, name))
            .addToBackStack("").commit()
    }

}