package com.arb.careasy.ui_layer.uistates

import com.arb.careasy.Data.model.Brand
import com.arb.careasy.Data.model.CarModel

data class HomeUiState(
    val cars: List<CarModel> = emptyList(),
    val brands: List<Brand> = emptyList(),
    val categories: List<String> = emptyList(),
    val selectedCategory: Int = 0,
    val currentSearchText: String = "Search your dream car...",
    val isLoading: Boolean = false,
    val error: String? = null
)