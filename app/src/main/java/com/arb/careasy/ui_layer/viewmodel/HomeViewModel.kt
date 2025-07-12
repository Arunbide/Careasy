package com.arb.careasy.ui_layer.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arb.careasy.Data.model.Brand
import com.arb.careasy.Data.model.CarModel
import com.arb.careasy.R
import com.arb.careasy.ui_layer.uistates.HomeUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _uiState = mutableStateOf(HomeUiState())
    val uiState: State<HomeUiState> = _uiState

    private val searchTexts = listOf(
        "Search your dream car...",
        "Tell us what you need",
        "Search for something specific?"
    )

    init {
        loadData()
        startSearchTextAnimation()
    }

    private fun loadData() {
        _uiState.value = _uiState.value.copy(
            cars = getCarList(),
            brands = getBrandList(),
            categories = getCategoryList()
        )
    }

    private fun startSearchTextAnimation() {
        viewModelScope.launch {
            var currentIndex = 0
            while (true) {
                delay(2500)
                currentIndex = (currentIndex + 1) % searchTexts.size
                _uiState.value = _uiState.value.copy(
                    currentSearchText = searchTexts[currentIndex]
                )
            }
        }
    }

    fun onCategorySelected(index: Int) {
        _uiState.value = _uiState.value.copy(selectedCategory = index)
    }

    fun onCarClicked(car: CarModel) {

    }

    fun onBrandClicked(brand: Brand) {
    }

    private fun getCarList() = listOf(
        CarModel(
            name = "Mercedes SL 63",
            price = "25000 INR",
            priceUnit = "day",
            horsepower = "585 hp",
            transmission = "Automatic",
            seats = "4 Seats",
            imageRes = R.drawable.car_1,
            tag = "01"
        ),

        CarModel(
            name = "Mercedes SL 63",
            price = "30000 INR",
            priceUnit = "day",
            horsepower = "591 hp",
            transmission = "Automatic",
            seats = "4 Seats",
            imageRes = R.drawable.car_1,
            tag = "03"
        ),
        CarModel(
            name = "Porsche 911 Turbo S",
            price = "30000 INR",
            priceUnit = "day",
            horsepower = "640 hp",
            transmission = "Automatic",
            seats = "2 Seats",
            imageRes = R.drawable.car_1,
            tag = "04"
        ),
    )

    private fun getBrandList() = listOf(
        Brand("Tesla", R.drawable.bbb_1),
        Brand("BMW", R.drawable.bbb_2),
        Brand("Mercedes", R.drawable.bbb_3),

    )

    private fun getCategoryList() = listOf("All", "Automatic", "Manual", "Hybrid", "Electric")
}