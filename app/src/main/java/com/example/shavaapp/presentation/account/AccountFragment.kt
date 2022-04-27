package com.example.shavaapp.presentation.account

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shavaapp.common.CurrentUser
import com.example.shavaapp.common.DeleteSessionCallBack
import com.example.shavaapp.common.EntryListener
import com.example.shavaapp.common.loadImgByUrl
import com.example.shavaapp.databinding.AccountFragmentBinding
import com.example.shavaapp.databinding.CodeWindowBinding
import com.example.shavaapp.presentation.MainActivity
import com.example.shavaapp.presentation.recycler.adapter.HistoryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : Fragment() {

    private val viewModel by viewModels<AccountViewModel>()
    private lateinit var binding: AccountFragmentBinding
    private val historyAdapter = HistoryAdapter()
    private lateinit var codeBinding: CodeWindowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AccountFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.orderHistoryRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = historyAdapter
        }
        viewModel.account.observe(viewLifecycleOwner) {
            CurrentUser.account.phone = it.phone
            CurrentUser.account.name = it.name
            binding.accountName.text = it.name
            binding.accountPhoneNumber.text = it.phone
            loadImgByUrl(it.imgUrl, binding.accimage)
        }
        viewModel.history.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                historyAdapter.items = it
            }
        }
        binding.editImg.setOnClickListener {
            openNameWindow()
        }
        binding.exitImg.setOnClickListener {
            deleteSession()
        }
        loadAccountInfo()
        loadAccountHistory()
    }

    private fun deleteSession() {
        viewModel.deleteSession(CurrentUser.account.mac, object : DeleteSessionCallBack {
            override fun onCallBack() {
                openEntryFragment()
            }
        })

    }

    private fun loadAccountInfo() {
        viewModel.loadAccount(CurrentUser.account.mac)
    }

    private fun loadAccountHistory() {
        viewModel.loadAccountHistory(CurrentUser.account.mac)
    }

    private fun openNameWindow() {
        val codeDialog = AlertDialog.Builder(requireContext())
            .setTitle("Name")
            .setMessage("Edit")
        codeBinding = CodeWindowBinding.inflate(LayoutInflater.from(requireContext()))
        codeBinding.codeField.hint = binding.accountName.text.toString()
        codeBinding.codeField.inputType = InputType.TYPE_CLASS_TEXT
        codeDialog.setView(codeBinding.root)
        codeDialog.setPositiveButton("Ok")
        { _, _ ->
            if (codeBinding.codeField.text.toString().isNotBlank()) {
                binding.accountName.text = codeBinding.codeField.text
            } else {
                Toast.makeText(requireContext(), "field is empty", Toast.LENGTH_LONG).show()
            }
        }
        codeDialog.setNegativeButton("Back")
        { _, _ -> requireActivity().supportFragmentManager.popBackStack() }

        requireActivity().supportFragmentManager.popBackStack()
        codeDialog.show()
    }

    private fun openEntryFragment() {
        (activity as MainActivity).openEntryFragment()
    }

}