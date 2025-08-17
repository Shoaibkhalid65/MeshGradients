package com.example.meshgradients.customShapes

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Sample3(){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Card(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(200.dp)
                .align(Alignment.Center)
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            Color.Cyan,
                            Color.Magenta
                        )
                    ),
                    shape = CustomCardShape(100f)
                ),
            shape = CustomCardShape(100f),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            )
        ) {
            Text(
                text = "Hello from the awesome card",
                modifier = Modifier.padding(start = 20.dp, top = 100.dp),
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp
            )
        }
    }
}
class CustomCardShape(val cornerRadius: Float=40f): Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val width=size.width
        val height=size.height
        val path= Path().apply {
            moveTo(cornerRadius,0f)
            lineTo(width-cornerRadius,height/3)
            arcTo(
                rect = Rect(
                    left = width-cornerRadius*2,
                    right = width,
                    top = height/3,
                    bottom = height/3+cornerRadius*2
                ),
                startAngleDegrees = 270f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )
            lineTo(width,height-cornerRadius)
            arcTo(
                rect = Rect(
                    left = width-cornerRadius*2,
                    right = width,
                    top = height-cornerRadius*2,
                    bottom = height
                ),
                startAngleDegrees = 0f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )
            lineTo(cornerRadius,height)
            arcTo(
                rect = Rect(
                    left =0f,
                    right = cornerRadius*2,
                    top = height-cornerRadius*2,
                    bottom = height
                ),
                startAngleDegrees = 90f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )
            lineTo(0f,cornerRadius)
            arcTo(
                rect = Rect(
                    left =0f,
                    right = cornerRadius*2,
                    top = 0f,
                    bottom = cornerRadius*2
                ),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )


        }
        return Outline.Generic(path)
    }
}
@Composable
fun Sample4() {
    val infiniteTransition = rememberInfiniteTransition()
    val animatedOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xFF42A5F5), Color(0xFF7E57C2), Color(0xFFFFA726)),
        start = Offset(0f, 0f),
        end = Offset(animatedOffset, animatedOffset)
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    brush = gradient,
                    shape = CustomCardShape(100f)
                )
                .clip(CustomCardShape(100f))
        ) {
            // Add content here
        }
    }
}


