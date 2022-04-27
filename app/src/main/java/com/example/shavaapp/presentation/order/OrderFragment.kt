package com.example.shavaapp.presentation.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.shavaapp.common.CurrentUser
import com.example.shavaapp.common.ShavaHolder
import com.example.shavaapp.databinding.OrderFragmentBinding
import com.example.shavaapp.presentation.recycler.HistoryPosition
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderFragment : Fragment() {

    private val viewModel by viewModels<OrderViewModel>()
    private lateinit var binding: OrderFragmentBinding
    private val holder = ShavaHolder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = OrderFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cats = arrayOf("Гикало, 9", "Петруся Бровки, 6")

        val adapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_dropdown_item_1line, cats
        )
        binding.autoCompleteTextView.apply {
            setAdapter(adapter)
            threshold = 2
            onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    binding.autoCompleteTextView.showDropDown()
                }
            }
        }
        var orderText = ""
        var fullPrice = 0.0
        holder.getSpecialList().forEach {
            orderText += it.item.name
            orderText += ":"
            orderText += if (it.item.description.isEmpty()) "classic"
            else it.item.description
            orderText += " - "
            orderText += it.number
            orderText += ";\n"
            fullPrice += it.item.price[0] * it.number
        }
        binding.apply {
            price.text = "Price:" + fullPrice
            bonus.text = "Bonus:" + fullPrice / 5.0
            name.setText(CurrentUser.account.name)
            number.setText(CurrentUser.account.phone)
            sendOrder.setOnClickListener {
                if (binding.name.text.isNotEmpty() && binding.number.text.isNotEmpty()) {
                    val order = HistoryPosition(
                        CurrentUser.account.mac,
                        (fullPrice / 5.0).toString(),
                        binding.comment.text.toString()
                    )
                    viewModel.addOrderToHistory(order)
                    Toast.makeText(context, "Order was sent", Toast.LENGTH_LONG).show()
                    holder.deleteAll()
                    requireActivity().supportFragmentManager.popBackStack()
                } else {
                    Toast.makeText(context, "Name or number is empty", Toast.LENGTH_LONG).show()
                }
            }
            comment.text = orderText
            toolBar.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }

}