package com.example.meshgradients.practice

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.meshgradients.ui.theme.AquaBlue
import com.example.meshgradients.ui.theme.BrightBlue
import com.example.meshgradients.ui.theme.CoralPink
import com.example.meshgradients.ui.theme.DeepNavy
import com.example.meshgradients.ui.theme.Indigo
import com.example.meshgradients.ui.theme.LightLilac
import com.example.meshgradients.ui.theme.PeachCoral
import com.example.meshgradients.ui.theme.SkyCyan
import com.example.meshgradients.ui.theme.SoftLavender
import kotlinx.coroutines.launch
import kotlin.random.Random


@Composable
fun AnimatedMeshGradient(
    colors: List<Color>,
    pointCount: Int = 9
) {
    require(colors.size == pointCount) {
        "You have to provide exactly $pointCount colors"
    }

    val animatables = remember {
        List(pointCount) {
            Animatable(randomOffset(), Offset.VectorConverter)
        }
    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        animatables.forEach { anim->
            scope.launch {
                while (true) {
                    val target = randomOffset()
                    anim.animateTo(
                        targetValue = target,
                        animationSpec = tween(
                            durationMillis = Random.nextInt(3000, 5000)
                        )
                    )
                }
            }
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        val points = animatables.map { anim ->
            Offset(anim.value.x * size.width, anim.value.y * size.height)
        }

        points.forEachIndexed { index, point ->
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(colors[index], Color.Transparent),
                    center = point,
                    radius = size.minDimension / 2
                ),
                radius = size.minDimension / 2,
                center = point
            )
        }
    }
}

private fun randomOffset(): Offset =
    Offset(Random.nextFloat(), Random.nextFloat())

@Composable
@Preview(showBackground = true)
fun MeshGradientPreview() {
    AnimatedMeshGradient(
        colors = listOf(
            Indigo, AquaBlue, SoftLavender, DeepNavy, CoralPink,
            SkyCyan, BrightBlue, LightLilac, PeachCoral
        )
    )
}


