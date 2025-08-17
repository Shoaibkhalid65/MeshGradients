package com.example.meshgradients.practice

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import com.example.meshgradients.meshGradient
import com.example.meshgradients.ui.theme.AquaBlue
import com.example.meshgradients.ui.theme.BrightBlue
import com.example.meshgradients.ui.theme.CoralPink
import com.example.meshgradients.ui.theme.DeepNavy
import com.example.meshgradients.ui.theme.Indigo
import com.example.meshgradients.ui.theme.LightLilac
import com.example.meshgradients.ui.theme.PeachCoral
import com.example.meshgradients.ui.theme.SkyCyan
import com.example.meshgradients.ui.theme.SoftLavender
import kotlinx.coroutines.delay
import kotlin.collections.listOf

@Composable
@Preview(showBackground = true)
fun Sample4(){
    val offset = remember {
        Animatable(Offset(0.5f, 0.5f), Offset.VectorConverter)
    }
    LaunchedEffect(Unit) {
        val radius = 0.3f
        var angle = 0f

        while (true) {
            val target = Offset(
                0.5f + radius * kotlin.math.cos(angle),
                0.5f + radius * kotlin.math.sin(angle)
            )

            offset.animateTo(
                targetValue = target,
                animationSpec = tween(
                    durationMillis = 50,
                    easing = LinearEasing
                )
            )
            angle += 0.05f
            if (angle > Math.PI * 2) angle -= (Math.PI * 2).toFloat()
        }
    }
    val points=listOf(
        listOf(
            Offset(0f,0f) to Indigo,
            Offset(.5f,0f) to AquaBlue,
            Offset(1f,0f) to SoftLavender
        ),
        listOf(
            Offset(0f,.5f) to DeepNavy,
            offset.value to CoralPink,
            Offset(1f,.5f) to SkyCyan
        ),
        listOf(
            Offset(0f,1f) to BrightBlue,
            Offset(.5f,1f) to LightLilac,
            Offset(1f,1f) to PeachCoral
        )
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .meshGradient(
                points=points,
                resolutionX = 32,
                resolutionY = 32
            )
    )
}