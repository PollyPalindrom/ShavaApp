package com.example.shavaapp.presentation.entry

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.shavaapp.R
import com.example.shavaapp.common.CurrentUser
import com.example.shavaapp.common.EntryListener
import com.example.shavaapp.databinding.AutificationBinding
import com.example.shavaapp.databinding.CodeWindowBinding
import com.example.shavaapp.databinding.EntryFragmentBinding
import com.example.shavaapp.databinding.RegistrationWindowBinding
import com.example.shavaapp.presentation.MainActivity
import com.example.shavaapp.presentation.account.AccountViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class EntryFragment : Fragment() {

    private val viewModel by viewModels<EntryViewModel>()
    private lateinit var binding: AutificationBinding
    private lateinit var regBinding: RegistrationWindowBinding
    private lateinit var codeBinding: CodeWindowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AutificationBinding.inflate(inflater, container, false)

        val macAddress = viewModel.getDeviceMacAddress()
        CurrentUser.account.mac = macAddress
        viewModel.isAuthorized.observe(viewLifecycleOwner) {
            if (it) {
                openMainFragment()
            }
        }
        viewModel.getAuthorizationStatus(macAddress)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.isSignSuccessful.observe(viewLifecycleOwner) {
            if (it) {
                addSession(CurrentUser.account.mac)
                addUser()
                openMainFragment()
            } else Toast.makeText(requireContext(), "verification failed", Toast.LENGTH_LONG)
                .show()
        }
        binding.signIn.setOnClickListener {
            openMainFragment()
        }
        binding.registration.setOnClickListener {
            val dialog = AlertDialog.Builder(requireContext())
                .setTitle("Registration")
                .setMessage("Fill all fields")
            val inflater = LayoutInflater.from(requireContext())
            regBinding = RegistrationWindowBinding.inflate(inflater)
            dialog.setView(regBinding.root)
            dialog.setPositiveButton("Ok")
            { _, _ ->
                if (regBinding.numberField.text.toString().isNotBlank()) {
                    lifecycleScope.launch(Dispatchers.IO) {
                        startPhoneNumberVerification(regBinding.numberField.text.toString())
                    }
                    openCodeWindow()
                } else {
                    Toast.makeText(requireContext(), "field is empty", Toast.LENGTH_LONG).show()
                }
            }
            dialog.setNegativeButton("Back")
            { _, _ -> requireActivity().supportFragmentManager.popBackStack() }
            dialog.show()
        }
    }

    private fun openCodeWindow() {
        val codeDialog = AlertDialog.Builder(requireContext())
            .setTitle("Registration")
            .setMessage("Enter the code")
        codeBinding = CodeWindowBinding.inflate(LayoutInflater.from(requireContext()))
        codeDialog.setView(codeBinding.root)
        codeDialog.setPositiveButton("Ok")
        { _, _ ->
            if (codeBinding.codeField.text.toString().isNotBlank()) {
                viewModel.verifyPhoneNumberWithCode(codeBinding.codeField.text.toString())
            } else {
                Toast.makeText(requireContext(), "wrong code", Toast.LENGTH_LONG).show()
            }
        }
        codeDialog.setNegativeButton("Back")
        { _, _ -> (activity as MainActivity).supportFragmentManager.popBackStack() }

        (activity as MainActivity).supportFragmentManager.popBackStack()
        codeDialog.show()
    }

    private fun openMainFragment() {
        (activity as MainActivity).openMainFragment()
    }

    private fun addSession(mac: String) {
        viewModel.addSession(mac)
    }

    private fun addUser() {
        viewModel.addUser(CurrentUser.account)
    }

    private fun startPhoneNumberVerification(number: String) {
        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(number)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(viewModel.getCallBacks())
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

}