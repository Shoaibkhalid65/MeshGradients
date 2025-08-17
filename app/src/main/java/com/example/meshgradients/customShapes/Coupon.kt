package com.example.meshgradients.customShapes

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meshgradients.meshGradient
import com.example.meshgradients.ui.theme.CouponColor1
import com.example.meshgradients.ui.theme.CouponColor10
import com.example.meshgradients.ui.theme.CouponColor2
import com.example.meshgradients.ui.theme.CouponColor3
import com.example.meshgradients.ui.theme.CouponColor4
import com.example.meshgradients.ui.theme.CouponColor5
import com.example.meshgradients.ui.theme.CouponColor6
import com.example.meshgradients.ui.theme.CouponColor7
import com.example.meshgradients.ui.theme.CouponColor8
import com.example.meshgradients.ui.theme.CouponColor9
import com.example.meshgradients.ui.theme.Libre_Bodoni

@Composable
@Preview(showBackground = true)
fun CouponSample1() {
    val infiniteTransition = rememberInfiniteTransition()
    val animatedColor1 = infiniteTransition.animateColor(
        initialValue = CouponColor6,
        targetValue = Color(0xFFdbf5ff),
        animationSpec = infiniteRepeatable(
            tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val animatedColor2 = infiniteTransition.animateColor(
        initialValue = CouponColor4,
        targetValue = Color(0xffdf9f3d),
        animationSpec = infiniteRepeatable(
            tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val points1 = listOf(
        listOf(
            Offset(0f, 0f) to CouponColor1,
            Offset(1f, 0f) to CouponColor2
        ),
        listOf(
            Offset(0f, 1f) to CouponColor3,
            Offset(1f, 1f) to animatedColor2.value
        )
    )
    val points2 = listOf(
        listOf(
            Offset(0f, 0f) to CouponColor5,
            Offset(.5f, 0f) to animatedColor1.value,
            Offset(1f, 0f) to CouponColor7
        ),
        listOf(
            Offset(0f, 1f) to CouponColor8,
            Offset(.5f, 1f) to CouponColor9,
            Offset(1f, 1f) to CouponColor10
        ),
    )
    Box(
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 12.dp)
            .fillMaxWidth()
            .height(150.dp)
            .clip(CouponShape(7.20.dp))
            .drawWithCache {
                val height = size.height
                val width = size.width
                onDrawWithContent {
                    drawRect(
                        color = Color.White,
                        topLeft = Offset(0f, 0f),
                        size = Size(width / 3f, height)
                    )
                    drawRect(
                        color = Color.Blue,
                        topLeft = Offset(width / 3f, 0f),
                        size = Size(width * 2 / 3, height)
                    )
                    drawLine(
                        color = Color.White,
                        start = Offset(width / 2, 0f),
                        end = Offset(width / 2, height),
                        strokeWidth = 10f,
                        cap = StrokeCap.Round,
                        pathEffect = PathEffect.dashPathEffect(
                            intervals = floatArrayOf(10f, 20f),
                            phase = 5f
                        )
                    )
                    drawContent()
                }
            }
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .rotate(-90f)
                    .meshGradient(
                        points = points1,
                        resolutionX = 32,
                        resolutionY = 32
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Text(
                    text = "Shopping Coupon",
                    style = TextStyle(
                        fontSize = 7.sp,
                        letterSpacing = TextUnit(3f, type = TextUnitType.Sp)
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "30%",
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontFamily = Libre_Bodoni
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1.08f)
                    .meshGradient(
                        points = points2,
                        resolutionX = 32,
                        resolutionY = 32
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Pakistan Zindabad",
                    style = TextStyle(
                        fontSize = 7.sp,
                        letterSpacing = TextUnit(3f, TextUnitType.Sp),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    ),
                )
                Text(
                    text = "Coupon",
                    modifier = Modifier.padding(5.dp),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 26.sp,
                    fontFamily = Libre_Bodoni
                )
                Text(
                    text = "VALID TILL OCTOBER 2025",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 6.sp,
                        letterSpacing = TextUnit(1f, TextUnitType.Sp)
                    )
                )
            }
        }
    }
}


