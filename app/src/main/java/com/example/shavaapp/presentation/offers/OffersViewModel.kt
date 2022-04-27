package com.example.shavaapp.presentation.offers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shavaapp.common.OfferCallBack
import com.example.shavaapp.domain.usecases.common.LoadOffersUseCase
import com.example.shavaapp.presentation.recycler.OfferPosition
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OffersViewModel @Inject constructor(private val loadOffersUseCase: LoadOffersUseCase) :
    ViewModel() {
    private val liveOffer: MutableLiveData<MutableList<OfferPosition>> = MutableLiveData()
    val offers: LiveData<MutableList<OfferPosition>>
        get() = liveOffer

    fun loadOffers() {
        viewModelScope.launch(Dispatchers.IO) {
            loadOffersUseCase.loadOffers(object : OfferCallBack {
                override fun onCallback(positionsList: List<OfferPosition>) {
                    val list = positionsList as MutableList<OfferPosition>
                    list.add(
                        OfferPosition(
                            "Free cola",
                            "https://grilltochka.com/wp-content/uploads/2020/08/shaurma600cola.jpg",
                            "Free cola\n" +
                                    "*Only in caffe"
                        )
                    )
                    liveOffer.postValue(list)
                }
            })
        }
    }
}