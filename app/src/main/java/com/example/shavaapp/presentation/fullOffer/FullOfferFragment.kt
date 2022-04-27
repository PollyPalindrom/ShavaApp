package com.example.shavaapp.presentation.fullOffer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.shavaapp.common.loadImgByUrl
import com.example.shavaapp.databinding.FullOfferFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FullOfferFragment : Fragment() {

    private lateinit var binding: FullOfferFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FullOfferFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.name.text = arguments?.getString(NAME).toString()
        loadImgByUrl(arguments?.getString(IMAGE_URL).toString(), binding.image)
        binding.comment.text = arguments?.getString(DESCRIPTION).toString()
        binding.toolBar.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    companion object {

        fun newInstance(url: String, name: String, description: String) =
            FullOfferFragment().apply {
                arguments = Bundle().apply {
                    putString(IMAGE_URL, url)
                    putString(NAME, name)
                    putString(DESCRIPTION, description)
                }
            }

        private const val IMAGE_URL = "IMAGE_URL"
        private const val NAME = "NAME"
        private const val DESCRIPTION = "DESCRIPTION"
    }

}