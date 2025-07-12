package com.arb.careasy.ui_layer

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import com.arb.careasy.R

data class CarData(
    val images: List<Int>,
    val name: String,
    val price: String,
    val description: String,
    val horsepower: String,
    val transmission: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarDescriptionScreen(
    carData: CarData = CarData(
        images = listOf(
            R.drawable.car_1,
            R.drawable.car_2,
            R.drawable.car_3,
            R.drawable.car_4
        ),
        name = "Mercedes SL\n63 AMG",
        price = "25000 INR/day",
        description = "The Mercedes SL 63 AMG is a sports car created using advanced technologies that have made it incredibly fast and powerful.",
        horsepower = "585 hp",
        transmission = "Automatic"
    ),
    onBackClick: () -> Unit = {},
    onInfoClick: () -> Unit = {},
    onBookNowClick: () -> Unit = {},
    navController: NavHostController
) {
    val pagerState = rememberPagerState(pageCount = { carData.images.size })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 15.dp)
    ) {
        TopAppBar(
            onBackClick = onBackClick,
            onInfoClick = onInfoClick
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 50.dp)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            ) { page ->
                Image(
                    painter = painterResource(id = carData.images[page]),
                    contentDescription = "Car image",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                itemsIndexed(carData.images) { index, imageRes ->
                    val isSelected = pagerState.currentPage == index
                    val borderColor by animateColorAsState(
                        targetValue = if (isSelected) Color(0xFF4A90E2) else Color.White.copy(alpha = 0.4f),
                        animationSpec = tween(200)
                    )

                    Box(
                        modifier = Modifier
                            .size(90.dp)
                            .border(
                                width = 2.dp,
                                color = borderColor,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clip(RoundedCornerShape(8.dp))
                            .clickable {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            }
                    ) {
                        Image(
                            painter = painterResource(id = imageRes),
                            contentDescription = "Car thumbnail",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(carData.images.size) { index ->
                    val isSelected = pagerState.currentPage == index
                    val width by animateFloatAsState(
                        targetValue = if (isSelected) 15f else 5f,
                        animationSpec = tween(200)
                    )
                    val alpha by animateFloatAsState(
                        targetValue = if (isSelected) 1f else 0.5f,
                        animationSpec = tween(200)
                    )

                    Box(
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                            .size(width = width.dp, height = 6.dp)
                            .background(
                                color = Color.White.copy(alpha = alpha),
                                shape = RoundedCornerShape(18.dp)
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            CarDetailsSection(
                carData = carData,
                onBookNowClick = onBookNowClick
            )
        }
    }
}

@Composable
fun TopAppBar(
    onBackClick: () -> Unit,
    onInfoClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CircularIconButton(
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            onClick = onBackClick
        )

        CircularIconButton(
            icon = Icons.Default.Info,
            onClick = onInfoClick
        )
    }
}

@Composable
fun CircularIconButton(
    icon: ImageVector,
    onClick: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = tween(100)
    )

    val backgroundColor by animateColorAsState(
        targetValue = if (isPressed) Color.White.copy(alpha = 0.1f) else Color.Transparent,
        animationSpec = tween(100)
    )

    val borderColor by animateColorAsState(
        targetValue = if (isPressed) Color.White.copy(alpha = 0.9f) else Color.White.copy(alpha = 0.7f),
        animationSpec = tween(100)
    )

    Box(
        modifier = Modifier
            .size(48.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .background(
                color = backgroundColor,
                shape = CircleShape
            )
            .border(
                width = 1.dp,
                color = borderColor,
                shape = CircleShape
            )
            .clip(CircleShape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onClick()
            }
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isPressed = true
                        tryAwaitRelease()
                        isPressed = false
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun CarDetailsSection(
    carData: CarData,
    onBookNowClick: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = carData.name,
                color = Color.White,
                fontSize = 25.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f)
            )

            Box(
                modifier = Modifier
                    .background(
                        color = Color.White.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(60.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = Color(0xFF4A90E2),
                        shape = RoundedCornerShape(60.dp)
                    )
                    .padding(horizontal = 15.dp, vertical = 8.dp)
            ) {
                Text(
                    text = carData.price,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = carData.description,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        Divider(
            color = Color.White.copy(alpha = 0.4f),
            thickness = 1.dp
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Specifications",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SpecificationItem(
                icon = R.drawable.gps,
                title = "Horsepower",
                value = carData.horsepower,
                modifier = Modifier.weight(1f)
            )

            SpecificationItem(
                icon = R.drawable.transmision,
                title = "Transmission",
                value = carData.transmission,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = onBookNowClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4A90E2)
            ),
            shape = RoundedCornerShape(36.dp)
        ) {
            Text(
                text = "Book Now",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun SpecificationItem(
    icon: Int,
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .border(
                    width = 1.dp,
                    color = Color.White.copy(alpha = 0.7f),
                    shape = CircleShape
                )
                .padding(15.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = title,
                modifier = Modifier.size(20.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )
        }

        Spacer(modifier = Modifier.width(15.dp))

        Column {
            Text(
                text = title,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = value,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 22.sp
            )
        }
    }
}

