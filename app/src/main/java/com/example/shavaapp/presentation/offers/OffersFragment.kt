package com.example.shavaapp.presentation.offers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shavaapp.R
import com.example.shavaapp.common.OffersFullScreenListener
import com.example.shavaapp.databinding.OffersFragmentBinding
import com.example.shavaapp.presentation.fullOffer.FullOfferFragment
import com.example.shavaapp.presentation.recycler.adapter.OfferAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OffersFragment : Fragment(), OffersFullScreenListener {

    private val viewModel by viewModels<OffersViewModel>()
    private lateinit var binding: OffersFragmentBinding
    private val offerAdapter = OfferAdapter(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = OffersFragmentBinding.inflate(inflater, container, false)

        viewModel.loadOffers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.offerRecycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = offerAdapter
        }
        viewModel.offers.observe(viewLifecycleOwner) {
            offerAdapter.submitList(viewModel.offers.value)
        }
    }

    override fun createFullScreen(url: String, name: String, description: String) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fContainerView, FullOfferFragment.newInstance(url, name, description))
            .addToBackStack("").commit()
    }

}