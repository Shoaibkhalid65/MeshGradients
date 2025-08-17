package com.example.meshgradients.customShapes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview(showBackground = true)
fun Sample1() {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.9f)),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            shape = CustomCurvedShape(100f,50f)

        ) {
            Text(
                text = "Hello Curved card",
                modifier = Modifier.padding(35.dp),
                fontSize = 32.sp
            )

        }
    }
}
class CustomCurvedShape(val cornerRadius: Float = 40f, val arcRadius: Float = 100f) :
    Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val width = size.width
        val height = size.height
        val path = Path().apply {
            addRoundRect(
                roundRect = RoundRect(
                    left = 0f,
                    right = width,
                    top = 0f,
                    bottom = height,
                    radiusY = cornerRadius,
                    radiusX = cornerRadius
                )
            )
            moveTo(0f, height / 2)
            addArc(
                oval = Rect(
                    center = Offset(0f, height / 2f),
                    radius = arcRadius
                ),
                startAngleDegrees = 270f,
                sweepAngleDegrees = 180f
            )
            moveTo(width, height / 2)
            addArc(
                oval = Rect(
                    center = Offset(width, height / 2f),
                    radius = arcRadius
                ),
                startAngleDegrees = 90f,
                sweepAngleDegrees = 180f
            )
        }
        return Outline.Generic(path)
    }
}


