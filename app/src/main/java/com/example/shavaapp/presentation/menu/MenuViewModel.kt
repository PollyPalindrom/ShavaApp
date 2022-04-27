package com.example.shavaapp.presentation.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shavaapp.common.MenuCallBack
import com.example.shavaapp.common.OfferCallBack
import com.example.shavaapp.data.database.FoodPosition
import com.example.shavaapp.domain.usecases.common.LoadOffersUseCase
import com.example.shavaapp.domain.usecases.menuFragmentUseCases.LoadMenuPositionsUseCase
import com.example.shavaapp.presentation.recycler.OfferPosition
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val loadMenuPositionsUseCase: LoadMenuPositionsUseCase,
    private val loadOffersUseCase: LoadOffersUseCase
) : ViewModel() {
    private val liveMenu: MutableLiveData<List<FoodPosition>> = MutableLiveData()
    val positions: LiveData<List<FoodPosition>>
        get() = liveMenu

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
                            "Free cola\n*Only in caffe"
                        )
                    )
                    liveOffer.postValue(list)
                }
            })
        }
    }

    fun loadMenuPositions() {
        viewModelScope.launch(Dispatchers.IO) {
            loadMenuPositionsUseCase.loadMenuPositions(object : MenuCallBack {
                override fun onCallback(positionsList: List<FoodPosition>) {
                    liveMenu.value = positionsList
                }
            })
        }
    }
}