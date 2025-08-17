package com.example.meshgradients.practice

import androidx.compose.animation.Animatable
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meshgradients.meshGradient
import com.example.meshgradients.ui.theme.Emerald500
import com.example.meshgradients.ui.theme.Indigo700
import com.example.meshgradients.ui.theme.Red500
import com.example.meshgradients.ui.theme.Sky400
import com.example.meshgradients.ui.theme.Sky500
import com.example.meshgradients.ui.theme.Sky600
import com.example.meshgradients.ui.theme.Slate50
import com.example.meshgradients.ui.theme.Zinc800
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
@Preview(showBackground = true)
fun ButtonSample1(){
    val scope= rememberCoroutineScope()
    var state  by remember { mutableIntStateOf(0) }
    val animatable = remember { Animatable(.1f) }
    LaunchedEffect(state) {
        when(state){
            1->{
                while (true){
                   animatable.animateTo(.4f, animationSpec = tween(500))
                   animatable.animateTo(.94f, animationSpec = tween(500))
                }
            }
            2->{
                animatable.animateTo(-.9f, animationSpec = tween(durationMillis = 900))
            }
            else -> {
                animatable.animateTo(.5f, animationSpec = tween(durationMillis = 900))
            }
        }
    }
    val color= remember { Animatable(Sky600) }
    LaunchedEffect(state) {
        when(state){
            1->{
                color.animateTo(Emerald500, animationSpec = tween(durationMillis = 500))
                color.animateTo(Sky400, animationSpec = tween(durationMillis = 500))
            }
            2->{
                color.animateTo(Red500,animationSpec = tween(durationMillis = 900))
            }
            else -> {
                color.animateTo(Sky500,animationSpec = tween(durationMillis = 900))
            }
        }
    }

    Box (
        Modifier
            .clip(CircleShape)
            .pointerHoverIcon(PointerIcon.Hand)
            .size(width = 250.dp, height = 122.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ){
                scope.launch {
                    if(state==0){
                        state=1
                        delay(4000)
                        state=2
                        delay(2000)
                        state=0
                    }
                }
            }
            .meshGradient(
                points = listOf(
                    listOf(
                        Offset(0f, 0f) to Zinc800,
                        Offset(.5f, 0f) to Zinc800,
                        Offset(1f, 0f) to Zinc800,
                    ),
                    listOf(
                        Offset(0f, .5f) to Indigo700,
                        Offset(.5f, animatable.value) to Indigo700,
                        Offset(1f, .5f) to Indigo700,
                    ),
                    listOf(
                        Offset(0f, 1f) to color.value,
                        Offset(.5f, 1f) to color.value,
                        Offset(1f, 1f) to color.value,
                    ),
                ),
                resolutionX = 64
            )
            .animateContentSize(
                animationSpec = spring(
                    stiffness = Spring.StiffnessMediumLow,
                    dampingRatio = Spring.DampingRatioMediumBouncy
                )
            ),
        contentAlignment = Alignment.Center
    ){
        when(state){
            1->{
                CircularWavyProgressIndicator(
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .align(Alignment.Center),
                    color = Slate50,
                    stroke= Stroke(width = 16f),
                )
            }
            2->{
                Text(
                    text = "Wrong!",
                    color=Slate50,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            else -> {
                Text(
                    text = "Log in",
                    color=Slate50,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

