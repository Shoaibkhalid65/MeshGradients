package com.example.meshgradients.practice

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meshgradients.customShapes.CustomCardShape
import com.example.meshgradients.meshGradient
import com.example.meshgradients.ui.theme.AquaBlue
import com.example.meshgradients.ui.theme.BrightBlue
import com.example.meshgradients.ui.theme.CoralPink
import com.example.meshgradients.ui.theme.DeepNavy
import com.example.meshgradients.ui.theme.Indigo
import com.example.meshgradients.ui.theme.Libre_Bodoni
import com.example.meshgradients.ui.theme.LightLilac
import com.example.meshgradients.ui.theme.PeachCoral
import com.example.meshgradients.ui.theme.SalmonGlow
import com.example.meshgradients.ui.theme.SkyCyan
import com.example.meshgradients.ui.theme.SoftAmber
import com.example.meshgradients.ui.theme.SoftLavender
import com.example.meshgradients.ui.theme.Tangerine
import com.example.meshgradients.ui.theme.WarmSand
import kotlin.math.cos
import kotlin.math.sin

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
@Preview(showBackground = true)
fun MeshGradientCardSample1(){
    val offset = remember {
        Animatable(Offset(0.5f, 0.5f), Offset.VectorConverter)
    }
    val animatedColor=remember { Animatable(CoralPink) }
    LaunchedEffect(Unit) {
        val radius = 0.3f
        var angle = 0f

        while (true) {
            val target = Offset(
                0.5f + radius * cos(angle),
                0.5f + radius * sin(angle)
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
    val colors=listOf(Tangerine, SoftAmber, WarmSand, SalmonGlow)
    LaunchedEffect(Unit) {
        while (true){
            animatedColor.animateTo(
                targetValue = colors.random(),
                animationSpec = tween(
                    durationMillis = 1500,
                    easing = LinearEasing
                )
            )
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
            offset.value to animatedColor.value,
            Offset(1f,.5f) to SkyCyan
        ),
        listOf(
            Offset(0f,1f) to BrightBlue,
            Offset(.5f,1f) to LightLilac,
            Offset(1f,1f) to PeachCoral
        )
    )
    Card(
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 12.dp)
            .fillMaxWidth()
            .clip(CustomCardShape(60f))
            .background(Color.Transparent, CustomCardShape(100f))
            .aspectRatio(3/2f)
            .meshGradient(
                points=points,
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ){
        Column (
            modifier = Modifier.padding(top = 30.dp).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Aveta Brows",
                fontFamily = Libre_Bodoni,
                style = MaterialTheme.typography.titleLargeEmphasized,
                fontSize = 42.sp,
                color = Color.White
            )
            Text(
                text = "оформление бровей, ламинирование ресниц",
                fontSize = 11.sp,
                color = Color.White
            )
        }
    }
}