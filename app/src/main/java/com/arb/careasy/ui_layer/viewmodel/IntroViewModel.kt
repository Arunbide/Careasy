package com.arb.careasy.ui_layer.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.arb.careasy.Data.model.IntroModel
import com.arb.careasy.R

class IntroViewModel: ViewModel() {
    var currentPage by mutableIntStateOf(0)
    fun onPageChanged(index:Int){
        currentPage= index
    }

    val introList = listOf(
        IntroModel(
            imageRes = R.drawable.intro_1,
            Title = "Elevate your drive with elegance",
            description = "Discover a world of premium vehicles at your fingertips. Unleash the thrill of driving in style. Your journey begins here."
        ),
        IntroModel(
            imageRes = R.drawable.intro_1,
            Title = "Seamless booking experience",
            description = "Book your dream car in just a few taps. Fast, secure, and tailored to your preferences."
        ),
        IntroModel(
            imageRes = R.drawable.intro_1,
            Title = "Unmatched luxury & comfort",
            description = "Experience the perfect blend of performance and sophistication every time you hit the road."
        )
    )

}