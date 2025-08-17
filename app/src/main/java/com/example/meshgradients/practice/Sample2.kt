package com.example.meshgradients.practice

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.meshgradients.meshGradient
import com.example.meshgradients.ui.theme.Emerald500
import com.example.meshgradients.ui.theme.Fuchsia400
import com.example.meshgradients.ui.theme.Indigo700
import com.example.meshgradients.ui.theme.Pink500
import com.example.meshgradients.ui.theme.Teal950
import com.example.meshgradients.ui.theme.Violet600
import kotlinx.coroutines.launch

@Composable
@Preview(showBackground = true)
fun Sample2(){
    val animatedPoint= remember { Animatable(.8f) }
    LaunchedEffect(Unit) {
        while (true){
            animatedPoint.animateTo(
                targetValue = .1f,
                animationSpec = spring(stiffness = Spring.StiffnessVeryLow)
            )
            animatedPoint.animateTo(
                targetValue = .9f,
                animationSpec = spring(stiffness = Spring.StiffnessVeryLow)
            )
        }
    }
    val leftColor=remember { Animatable(Pink500) }
    val middleColor=remember { Animatable(Pink500) }
    val rightColor=remember { Animatable(Pink500) }
    LaunchedEffect(Unit) {
        val colors=listOf(Pink500, Violet600, Fuchsia400, Emerald500)
        fun animate(color: Animatable<Color, AnimationVector4D>){
            launch {
                while (true){
                    color.animateTo(
                        targetValue = colors.random(),
                        animationSpec = tween(
                            durationMillis = (300..500).random(),
                            easing = LinearOutSlowInEasing
                        )
                    )
                }
            }
        }
        listOf(leftColor,middleColor,rightColor).map { animate(it) }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .meshGradient(
                points = listOf(
                    listOf(
                        Offset(0f,0f) to Teal950,
                        Offset(.5f,0f) to Teal950,
                        Offset(1f,0f) to Teal950
                    ),
                    listOf(
                        Offset(0f,.5f) to Indigo700,
                        Offset(.5f,animatedPoint.value) to Indigo700,
                        Offset(1f,.5f) to Indigo700
                    ),
                    listOf(
                        Offset(0f,1f) to leftColor.value,
                        Offset(.5f,1f) to middleColor.value,
                        Offset(1f,1f) to rightColor.value
                    )
                ),
                resolutionX = 32,
                resolutionY = 32

            ),
    )

}