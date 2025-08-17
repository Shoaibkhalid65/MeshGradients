package com.example.meshgradients.practice

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meshgradients.customShapes.CustomCurvedShape
import com.example.meshgradients.meshGradient
import com.example.meshgradients.ui.theme.CardColor1
import com.example.meshgradients.ui.theme.CardColor2
import com.example.meshgradients.ui.theme.CardColor3
import com.example.meshgradients.ui.theme.CardColor4
import com.example.meshgradients.ui.theme.LightWhite

@Composable
@Preview(showBackground = true)
fun MeshGradientCardSample2(){
    val animatable=remember { Animatable(Offset(.5f,.5f),Offset.VectorConverter) }
    LaunchedEffect(Unit) {
        while (true){
            animatable.animateTo(randomOffset1(),tween(3400,easing= LinearEasing))
        }
    }
    val points=listOf(
        listOf(
            Offset(0f,0f) to CardColor4,
            Offset(.5f,0f) to CardColor1,
            Offset(1f,0f) to CardColor1
        ),
        listOf(
            Offset(.0f,.5f) to CardColor3,
            animatable.value to CardColor2,
            Offset(1f,.5f) to CardColor4
        ),
        listOf(
            Offset(0f,1f) to CardColor2,
            Offset(.5f,1f) to CardColor3,
            Offset(1f,1f) to CardColor4
        )
    )
    Card (
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 12.dp)
            .fillMaxWidth()
            .clip(CustomCurvedShape(60f,30f))
            .aspectRatio(3/2f)
            .meshGradient(
                points=points,
                resolutionY = 32,
                resolutionX = 32
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ){
        Column(
            modifier = Modifier.padding(start = 36.dp, top = 60.dp)
        ) {
            Text(
                text = "CARD NUMBER",
                color = LightWhite,
                letterSpacing = TextUnit(2f, TextUnitType.Sp),
            )
            Text(
                text = "4129 4201 4230 2310",
                modifier = Modifier.padding(top = 5.dp),
                color = Color.White,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                letterSpacing = TextUnit(2.5f, TextUnitType.Sp)
            )
            Row(
                modifier = Modifier.padding(top = 18.dp)
            ) {
                Column {
                    Text(
                        text = "VALID",
                        color = LightWhite,
                        letterSpacing = TextUnit(2f, TextUnitType.Sp),
                        fontSize = 13.5.sp
                    )
                    Text(
                        text = "06/27",
                        color = Color.White,
                        letterSpacing = TextUnit(2.5f, TextUnitType.Sp),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(3.dp)
                    )
                }
                Column (modifier = Modifier.padding(start = 68.dp)){
                    Text(
                        text = "CVV",
                        color = LightWhite,
                        letterSpacing = TextUnit(2f, TextUnitType.Sp),
                        fontSize = 13.5.sp
                    )
                    Text(
                        text = "377",
                        color = Color.White,
                        letterSpacing = TextUnit(2.5f, TextUnitType.Sp),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(3.dp)
                    )
                }
            }
        }
    }
}