package com.arb.careasy.ui_layer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.arb.careasy.Data.model.IntroModel
import com.arb.careasy.R
import com.arb.careasy.ui_layer.navigation.Homescreen
import com.arb.careasy.ui_layer.viewmodel.IntroViewModel
import kotlinx.coroutines.launch


@Composable
fun IntroScreenUI(onFinished: () -> Unit = {}, navController: NavHostController) {
    val viewModel: IntroViewModel = viewModel()

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { viewModel.introList.size }
    )

    val scope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage) {
        viewModel.onPageChanged(pagerState.currentPage)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        HorizontalPager(state = pagerState) { page ->
            IntroPage(model = viewModel.introList[page])
        }

        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 60.dp, end = 20.dp)
        ) {
            viewModel.introList.forEachIndexed { i, _ ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .height(4.dp)

                        .width(if (viewModel.currentPage == i) 20.dp else 8.dp)
                        .background(
                            color = if (viewModel.currentPage == i) Color.White else Color.Gray,
                            shape = RoundedCornerShape(18.dp)
                        )
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.lux_drive),
            contentDescription = "Logo",
            modifier = Modifier
                .padding(start = 20.dp, top = 20.dp)
                .size(100.dp)
                .align(Alignment.TopStart)
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(30.dp)
        ) {
            Button(
                onClick = {
                    if (viewModel.currentPage == viewModel.introList.lastIndex) {
                        onFinished()
                        navController.navigate(Homescreen)
                    } else {
                        scope.launch {
                            pagerState.animateScrollToPage(viewModel.currentPage + 1)
                        }
                    }
                },
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth().height(70.dp).padding(bottom = 10.dp)
            ) {
                Text(
                    text = "Let's Go",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W500
                )
            }
        }
    }
}

@Composable
fun IntroPage(model: IntroModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = model.imageRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Image(
            painter = painterResource(id = R.drawable.shadow),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            alpha = 0.9f
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))
        )

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(horizontal = 20.dp, vertical = 150.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = model.Title,
                style = MaterialTheme.typography.displaySmall
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = model.description,
                style = MaterialTheme.typography.bodyMedium ,
                color = Color.White.copy(alpha = 0.65f),
                lineHeight = 20.sp
            )
        }
    }
}