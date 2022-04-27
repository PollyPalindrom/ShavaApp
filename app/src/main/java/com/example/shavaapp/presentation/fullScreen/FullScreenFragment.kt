package com.example.shavaapp.presentation.fullScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.shavaapp.common.ShavaHolder
import com.example.shavaapp.common.loadImgByUrl
import com.example.shavaapp.data.database.FoodPosition
import com.example.shavaapp.databinding.FullScreenFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FullScreenFragment : Fragment() {

    private lateinit var binding: FullScreenFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FullScreenFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    private val holder = ShavaHolder
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.name.text = arguments?.getString(NAME).toString()
        loadImgByUrl(arguments?.getString(IMAGE_URL).toString(), binding.image)

        binding.add.setOnClickListener {
            holder.addShava(
                FoodPosition(
                    0, arguments?.getString(NAME).toString(), "", createDescription(), listOf(1.0),
                    arrayListOf(1.0)
                )
            )
            requireActivity().supportFragmentManager.popBackStack()
        }
        binding.toolBar.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun createDescription(): String {
        var description = ""
        if (binding.firstCheckBox.isChecked) {
            description += binding.firstCheckBox.text.toString()
            description += ";"
        }
        if (binding.secondCheckBox.isChecked) {
            description += binding.secondCheckBox.text.toString()
            description += ";"
        }
        if (binding.thirdCheckBox.isChecked) {
            description += binding.thirdCheckBox.text.toString()
            description += ";"
        }
        return description
    }

    companion object {
        fun newInstance(url: String, name: String): FullScreenFragment =
            FullScreenFragment().apply {
                arguments = Bundle().apply {
                    putString(IMAGE_URL, url)
                    putString(NAME, name)
                }
            }

        private const val IMAGE_URL = "IMAGE_URL"
        private const val NAME = "NAME"
    }

}