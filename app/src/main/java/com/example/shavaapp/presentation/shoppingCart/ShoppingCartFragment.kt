package com.example.shavaapp.presentation.shoppingCart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shavaapp.R
import com.example.shavaapp.common.ShavaHolder
import com.example.shavaapp.databinding.ShoppingCartFragmentBinding
import com.example.shavaapp.presentation.order.OrderFragment
import com.example.shavaapp.presentation.recycler.SimpleItemTouchHelperCallback
import com.example.shavaapp.presentation.recycler.adapter.CartAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingCartFragment : Fragment() {

    private lateinit var binding: ShoppingCartFragmentBinding
    private val holder = ShavaHolder
    private val orderAdapter = CartAdapter()
    private val touchHelper = ItemTouchHelper(SimpleItemTouchHelperCallback(orderAdapter))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ShoppingCartFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.orderRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = orderAdapter
        }
        orderAdapter.submitList(holder.getSpecialList())
        touchHelper.attachToRecyclerView(binding.orderRecycler)
        binding.make.setOnClickListener {
            createOrderScreen()
        }
    }

    private fun createOrderScreen() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fContainerView, OrderFragment())
            .addToBackStack("").commit()
    }

}