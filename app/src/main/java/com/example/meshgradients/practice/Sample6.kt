package com.example.meshgradients.practice


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
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
import com.example.meshgradients.ui.theme.LightRose
import com.example.meshgradients.ui.theme.PaleOrange
import com.example.meshgradients.ui.theme.PeachCoral
import com.example.meshgradients.ui.theme.PeachPink
import com.example.meshgradients.ui.theme.SkyCyan
import com.example.meshgradients.ui.theme.SoftLavender
import com.example.meshgradients.ui.theme.SunYellow
import com.example.meshgradients.ui.theme.WarmPink
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.random.nextInt


@Composable
fun FloatingColoredCircles(
    colors: List<Color>
){
    val offsetConverter= TwoWayConverter<Offset, AnimationVector2D>(
        convertToVector = {offset-> AnimationVector2D(offset.x,offset.y)},
        convertFromVector = {animationVector2D -> Offset(animationVector2D.v1,animationVector2D.v2)}
    )
    val pointCount:Int=colors.size
    val animatables= remember{
        List(pointCount){
            Animatable(
                initialValue = randomOffset1(), offsetConverter
            )
        }
    }
    val scope= rememberCoroutineScope()
    LaunchedEffect(Unit) {
        animatables.forEach { animatable ->
            scope.launch {
                while (true){
                    animatable.animateTo(
                        targetValue = randomOffset1(),
                        animationSpec = tween(
                            durationMillis = Random.nextInt(3000..6000)
                        )
                    )
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawWithCache{
            onDrawBehind {
                val points=animatables.map { animatable ->
                    Offset(animatable.value.x*size.width,animatable.value.y*size.height)
                }
                points.forEachIndexed { index,offset ->
                    val radius=size.minDimension/2
                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = listOf(colors[index],Color.Transparent),
                            center=offset,
                            radius = radius
                        ),
                        radius = radius,
                        center=offset
                    )
                }
            }
        },
        contentAlignment = Alignment.Center
    ) {
        ButtonSample1()
    }
}
fun randomOffset1()= Offset(Random.nextFloat(), Random.nextFloat())


@Composable
@Preview(showBackground = true)
fun Preview(){
    FloatingColoredCircles(
        listOf(
            Indigo, AquaBlue, SoftLavender, DeepNavy, CoralPink,
            SkyCyan, BrightBlue, LightLilac, PeachCoral,
            PeachPink, WarmPink, LightRose, PaleOrange, SunYellow,
        )
    )
}